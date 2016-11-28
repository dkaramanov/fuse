/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.estafet.fuse.training.bankx_routes.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.Main;

import com.estafet.fuse.training.bankx_models.bean.IbanWrapper;

/**
 * A Camel Router
 */
public class MessageProducerRouteBuilder extends RouteBuilder {

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
    	from("jetty:http://localhost:10024/iban").
    	onException(Throwable.class).
    		handled(true).
    		setHeader(Exchange.HTTP_RESPONSE_CODE).constant(Integer.valueOf(500)).
    		setBody(simple("Error during request...")).
    	end().			
        routeId("entry").
        unmarshal().json(JsonLibrary.Jackson, IbanWrapper.class).
        split(simple("${body.getIbans()}")).
        setHeader("IbanTimestampOfRequest", simple("${date:now:yyyy-MM-dd-hh-mm-ss}")).
        to("activemq:queue:estafet.iban.report.splitted.queue").
        end();
    }
}
