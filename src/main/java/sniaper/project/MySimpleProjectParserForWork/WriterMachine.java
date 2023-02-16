package sniaper.project.MySimpleProjectParserForWork;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class WriterMachine {

	private Detective detective;
	
	public WriterMachine(Detective detective) {
		this.detective = detective;
	}
	
	public void writeDown() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/main/resources/Detect.dat"))) {
			oos.writeObject(detective);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
