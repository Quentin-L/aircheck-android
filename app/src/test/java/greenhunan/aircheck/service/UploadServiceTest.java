package greenhunan.aircheck.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import greenhunan.aircheck.model.SampleData;

import static org.junit.Assert.*;

/**
 * Created by Quentin on 9/18/16.
 */
public class UploadServiceTest {

    SampleData data = new SampleData(21, "2016-1-1 16:30:00", 2.00, 2.00, 3.00);
    UploadService uploadService;

    @Before
    public void setup() {
        uploadService = new UploadService();
    }

    @Test
    public void testUpdate() throws Exception {
        uploadService.update(data);
        Thread.sleep(10000);
    }
}