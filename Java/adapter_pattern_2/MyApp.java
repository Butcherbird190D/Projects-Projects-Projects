package adapter_pattern_2;

public class MyApp {
	private IUSDevice usDevice;
	public MyApp(IUSDevice usDevice) {
		this.usDevice = usDevice;
	}
	public void use() {
		usDevice.plugIntoUSOutlet();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		USDevice usDevice = new USDevice();
		//MyApp myApp = new MyApp(usDevice);
		//myApp.use();
		
		EuroDevice euroDevice = new EuroDevice();
		//MyApp yourApp = new MyApp(eruoDevice);
		usDevice.plugIntoUSOutlet();
		//euroDevice.plugIntoUSOutlet();
		
		USAdapter usAdapter = new USAdapter(euroDevice);
		//MyApp theirApp = new MyApp(usAdapter);
		//theirApp.use();
		usAdapter.plugIntoUSOutlet();
	}

}
