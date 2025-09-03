package com.example.bookcatalog.config;

import com.example.bookcatalog.ws.BookCatalogService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean
    public Endpoint bookCatalogEndpoint(Bus bus, BookCatalogService bookCatalogService) {
        EndpointImpl endpoint = new EndpointImpl(bus, bookCatalogService);
        endpoint.publish("/book-catalog");
        return endpoint;
    }
}


