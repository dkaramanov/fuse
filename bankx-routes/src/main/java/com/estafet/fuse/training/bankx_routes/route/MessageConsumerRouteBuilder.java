package com.estafet.fuse.training.bankx_routes.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;
import org.apache.camel.spring.Main;

import com.estafet.fuse.training.bankx_models.bean.AccountsWrapper;
import com.estafet.fuse.training.bankx_routes.processor.IbanAggregationStrategy;
import com.estafet.fuse.training.bankx_routes.processor.IbanEnrichStrategy;
import com.estafet.fuse.training.bankx_routes.processor.IbanProcessor;

public class MessageConsumerRouteBuilder extends RouteBuilder {
	
	private final static IbanEnrichStrategy ibanEnrichStrategy = new IbanEnrichStrategy();
	private final static IbanProcessor processor =  new IbanProcessor();
	private final static IbanAggregationStrategy ibanAggregationStrategy = new IbanAggregationStrategy();

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main.main(args);
    }

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
    	BindyCsvDataFormat bindy = new BindyCsvDataFormat(AccountsWrapper.class);
    	bindy.setLocale("default");
    	
    	from("activemq:queue:estafet.iban.report.splitted.queue").
    	onException(Throwable.class).
			handled(true).
			setHeader(Exchange.HTTP_RESPONSE_CODE).constant(Integer.valueOf(500)).
			setBody(simple("Error during consume message...")).
		end().	
    	log("From QUEUE ::: " + simple("${body}")).
    	process(processor).
    	enrich("direct:enricher", ibanEnrichStrategy).
    	aggregate(header("IbanTimestampOfRequest"), ibanAggregationStrategy).
    	completionInterval(2000).
    	marshal().json(JsonLibrary.Jackson, true).
    	setHeader("CamelFileName", simple("Iban${date:now:yyyy-MM-dd-hh-mm-ss}.txt")).
    	to("ftp://WC-FTP@localhost:21/messages/?password=DiK@1611");
  	
    	from("direct:enricher").process("fakeDataProcessor");
    	
    	from("ftp://WC-FTP@localhost:21/messages/?password=DiK@1611").routeId("scan").
        filter(header("CamelFileName").endsWith(".txt")).
        idempotentConsumer(header("CamelFileName"),
                        MemoryIdempotentRepository.memoryIdempotentRepository(200)).
        // Log the content of the files.
        log(LoggingLevel.INFO, "File ${in.header.CamelFileName} received with body: ${in.body}").
        unmarshal().json(JsonLibrary.Jackson, AccountsWrapper.class).
        marshal(bindy).
        setHeader("CamelFileName", simple("${in.header.CamelFileName.replace(\".txt\", \".csv\")}")).
        to("file:///d:/target1/messages/uk/");
    	
    	from("quartz://myTimer?trigger.repeatInterval=10000&trigger.repeatCount=5").
    	log("Time ::: " + simple("${date:now:yyyy-MM-dd-hh-mm-ss}"));
    }
}
