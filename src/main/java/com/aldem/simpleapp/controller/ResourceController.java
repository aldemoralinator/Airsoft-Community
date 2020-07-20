/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldem.simpleapp.controller;
 
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ResourceController 
{
    private final String genPath = "/home/mint/WORKSPACES/Netbeans/";
    private final String cssPath = 
            genPath + "simpleapp/src/main/resources/static/css/";
    private final String imagePath = 
            genPath + "simpleapp/src/main/resources/static/images/";
    
    @RequestMapping (
        value = "/static/css/{resource}", 
        method = RequestMethod.GET, 
        produces = "text/css; charset=utf-8"
    )
    @ResponseBody
    public String getCss(@PathVariable String resource)
    {
        try {
            String content = Files.readString(Paths.get(cssPath + resource));
            return content;
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "resource not found");
        }
    }
    
    @RequestMapping (
        value = "/static/images/{resource}", 
        method = RequestMethod.GET, 
        produces = MediaType.IMAGE_PNG_VALUE
    )
    @ResponseBody
    public byte[] getImage(@PathVariable String resource)
    {
        try {
            byte [] content = Files.readAllBytes(Paths.get(imagePath + resource));
            return content;
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "resource not found");
        }
    }

}
