package com.project.hems.auth_service_hems.GridDataGenerated;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
//import reactor.core.publisher.Flux;

import java.io.OutputStream;
import java.time.Duration;
import java.util.Random;

@RestController
public class LiveDataDisplay {

    @GetMapping("/live-stream")
    public StreamingResponseBody streamSiteData(HttpServletResponse response){
        response.setContentType("text/event-stream");

        return (OutputStream outputStream) ->{
            try{
                for(int i=0;i<20;i++){
                    double reading=new Random().nextDouble()*10;
                    String update="Site Solar reading:- "+reading+"\n";
                    outputStream.write(update.getBytes());
                    outputStream.flush();
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        };
    }
    //we use insted of this GRPC ..
    //we use also spring reactive
    //spring reactive jode work kariee and stream of data jato hoy controller mathi toh producer define karvu pade
    //produces define nai kariee toh response ma List of String return karse ek ek jem ready thayy che server per em nai mokle
    //ek sathe akhi mokalse
   /*@GetMapping(value = "/live-stream/reactive",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamSolarPower(){
        return Flux.interval(Duration.ofSeconds(1))
                .take(20)
                .map(i->{
                    double solarPower=new Random().nextDouble()*10;
                    return "Solar Power is "+solarPower;
                });

    */
}
//NOTE:-
    /*
    in reactive approch we cannot work with the raw object we need List of object
    and if it is a List of object then we use Flux and if it is a Single object then we go with Mono
    	Mono<T>	“I will give you 0 or 1 value sometime in the future”
    	Flux<T>	“I will give you 0 to N values over time”
     */



