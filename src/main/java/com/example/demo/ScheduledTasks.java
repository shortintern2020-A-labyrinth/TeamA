package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.service.EmologService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Yuta Takayama
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    EmologService emologService;

//    @Scheduled
//    fixedDelay:	taskの実行完了時点から指定時間後に次のtaskを実行する. 単位はms.
//    fixedRate:	taskの実行開始時点から指定時間後に次のtaskを実行する. 単位はms.
//    initialDelay:	指定時間後に最初のtaskを開始する. 単位はms. fixedDelay又はfixedRateと組み合わせて使用する.
//    cron:	        cronで指定した周期でtaskを実行する.
//    zone:	        cronの起動時間のタイムゾーンを指定する. 未指定時はデフォルトのtimezoneを使用する.
//    @Scheduled(fixedRate = 300000)
    public void createEmolog() throws Exception {
        emologService.createEmolog();
    }
}
