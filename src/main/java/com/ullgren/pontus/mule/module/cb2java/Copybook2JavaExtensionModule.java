/**
* Copyright (c) Pontus Ullgren
* The software in this package is published under the terms of the GPL v2.0
* license, a copy of which has been included with this distribution in the
* LICENSE.md file.
**/

/**
 * This file was automatically generated by the Mule Development Kit
 */
package com.ullgren.pontus.mule.module.cb2java;

import org.mule.api.annotations.Module;
import org.mule.api.ConnectionException;
import org.mule.api.MuleContext;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.param.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.sf.cb2java.copybook.Copybook;
import net.sf.cb2java.copybook.CopybookParser;
import net.sf.cb2java.data.Data;
import net.sf.cb2java.data.Record;

/**
 * Generic module
 *
 * @author Pontus Ullgren
 */
@Module(name="copybook2javaextension", schemaVersion="1.0-SNAPSHOT",friendlyName="Mule Module CB2Java")
public class Copybook2JavaExtensionModule
{
    /**
     * Configurable
     */
    @Configurable
    private String copybook;

    @Configurable
    @Optional
    private String copybookName = "NA";
    
    @Autowired
    private MuleContext context;

    /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/Copybook2JavaExtension-connector.xml.sample copybook2javaextension:copybook-to-record}
     *
     * @param content Content to be processed
     * @return Some string
     * @throws IOException 
     */
    @Processor
    public List<Record> copybookToRecord(@Payload final InputStream input) throws IOException
    {
    	Resource resource = new ClassPathResource(getCopybook());
        // Parse copybook
        Copybook copybook = CopybookParser.parse(this.copybookName,
        		resource.getInputStream());
        // Parse data
        List<Record> results = copybook.parseData(input);
        return results;
    }
    
    /**
     * Set property
     *
     * @param myProperty My property
     */
    public void setCopybook(String copybook)
    {
        this.copybook = copybook;
    }

    public String getCopybook(){
        return this.copybook;
    }
    
    public void setCopybookName(String copybookName) {
		this.copybookName = copybookName;
	}
    
    public String getCopybookName() {
		return copybookName;
	}
}
