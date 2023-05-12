package com.example.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class SampleScheduler {
    // 스케줄링에서 Batch 사용 시, JobLauncher를 직접 사용

    private final Job helloWorldJob;
    private final JobLauncher jobLauncher;

    public void doJob() {
        JobParameters jobParameters = new JobParameters(
                Collections.singletonMap()
        )

        jobLauncher.run(helloWorldJob, )
    }
}
