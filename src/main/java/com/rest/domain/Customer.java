/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author pumpkin
 */
@XmlRootElement(name="customer")
public class Customer {
    @XmlElement
    public int id;
    @XmlElement
    public String firstname;
    @XmlElement
    public String lastname;
    @XmlElement
    public String email;
}
