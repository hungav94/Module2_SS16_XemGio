package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {

    @GetMapping("/worldclock")
    public String getTimeByTimezone(Model model, @RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {
        // get current time at local
        Date date = new Date();
        // get timezone by the local
        TimeZone local = TimeZone.getDefault();
        // get timezone by the specified city
        TimeZone locale = TimeZone.getTimeZone(city);

        // calculator the current time in the specified city
        long locale_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
        // reset the date by locale_time
        date.setTime(locale_time);

        // set the data sent to the view
        model.addAttribute("city", city);
        model.addAttribute("date", date);
        return "index";
    }
}
