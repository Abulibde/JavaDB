package com.example.springintro.model.entity.dto;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;

import java.math.BigDecimal;


public interface BookInformation {

   String getTitle();
   EditionType getEditionType();
   AgeRestriction getAgeRestriction();
   BigDecimal getPrice();

}
