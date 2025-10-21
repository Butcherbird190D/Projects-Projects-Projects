package adapter_pattern_2;

public class USAdapter implements IUSDevice {
	
	private EuroDevice euroDevice;
	
	public USAdapter(EuroDevice euroDevice) {
		this.euroDevice = euroDevice;
	}


	@Override
	public void plugIntoUSOutlet() {
		// TODO Auto-generated method stub
		System.out.println("connecting to the adapter");
		euroDevice.plugIntoEurOutlet();
	}


	
}
