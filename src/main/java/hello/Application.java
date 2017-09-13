package hello;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	
	@RequestMapping("/")
	
	public Map home() throws SocketException {
		Map map= new HashMap();
		List list= new ArrayList();
		Enumeration e = NetworkInterface.getNetworkInterfaces();
		while(e.hasMoreElements())
		{
		    NetworkInterface n = (NetworkInterface) e.nextElement();
		    Enumeration ee = n.getInetAddresses();
		    while (ee.hasMoreElements())
		    {
		        InetAddress i = (InetAddress) ee.nextElement();
		        list.add(i.getHostAddress());
		        
		        
		    }
		}
		map.put("NetworkInterfaces", list);
		Map<String, String> env = System.getenv();
		Set<String> keySet = env.keySet();
		for (String key : keySet) 
		{
			
			map.put("env:"+key, env.get(key));
		}
		
		return map;
		
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
