package sniaper.project.MySimpleProjectParserForWork;

import java.io.UnsupportedEncodingException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {
	public static void main(String[] args) throws UnirestException, UnsupportedEncodingException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClassConfigBean.class);
		Detective detective = context.getBean("DetectedBean", Detective.class);
		detective.setStatus(EnumStatusAddInfo.Y);
		
		double[][] arrayFlotCoordiantes = {
				{64.623, 39.180},
				{59.946, 30.335},
				{59.48, 30.15},
				{68.98639589674475, 33.07708192476715},
				{69.0693701216066, 33.408},
				{64.56720138147703, 39.84640947795396},
				{43.87212670229469, 135.4594585056625}};
		
		for (int i = 0; i < arrayFlotCoordiantes.length; i++) {
				detective.setCoordsCity(arrayFlotCoordiantes[i][0], arrayFlotCoordiantes[i][1]);
				detective.genInfo();
				detective.showInfo();
				System.out.println("\n");
		}
		
		context.close();
	}
}
