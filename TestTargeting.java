public class TestTargeting
{
	public static void main( String[] args )
	{
		TargetingStrategy strat;
		
		strat = new TargetSingleAlly();
		
		System.out.println( strat.toString() );
	}
}
