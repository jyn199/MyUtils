package com.jyn.utils.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job{

	public void work() {
		System.out.println("Quartz的任务调度！！！");
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("Quartz的任务调度！！！");
	}
}