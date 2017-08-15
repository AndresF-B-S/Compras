/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapris.quartz.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author APRENDIZ
 */
public class QuartzTest implements Job {

    @Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
        try {
            
        System.out.println("Se pudo enviar los email");
        } catch (Exception e) {
            e.printStackTrace();
        System.out.println("Nos se pudo enviar el email");
        }
        
    }

}
