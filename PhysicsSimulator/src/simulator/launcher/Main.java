package simulator.launcher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/*
 * Examples of command-line parameters:
 * 
 *  -h
 *  -i resources/examples/ex4.4body.txt -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl ftcg
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl nlug
 *
 */

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.factories.BasicBodyBuilder;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.FallingToCenterGravityBuilder;
import simulator.factories.MassLosingBodyBuilder;
import simulator.factories.NewtonUniversalGravitationBuilder;
import simulator.factories.NoGravityBuilder;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.view.MainWindow;

public class Main {

	// default values for some parameters
	//
	private final static Double _dtimeDefaultValue = 10000.0;

	// some attributes to stores values corresponding to command-line parameters
	//
	private static Double _dtime = null;
	private static String _inFile = null;
	private static JSONObject _gravityLawsInfo = null;
	private static String _outFile=null;
	private static int _steps=150;
	private static boolean startBatch = true;
	// factories
	private static Factory<Body> _bodyFactory;
	private static Factory<GravityLaws> _gravityLawsFactory;


	private static void init() {
		// initialize the bodies factory
		// ...
		inicializaBodyBuilders();
		inicializaGravityLawsBuilders();
		

		// initialize the gravity laws factory
		// ...
	}
	
	private static void inicializaBodyBuilders()
	{
		ArrayList<Builder<Body>> bodyBuilders = new ArrayList<>();
		bodyBuilders.add(new BasicBodyBuilder());
		bodyBuilders.add(new MassLosingBodyBuilder());
		_bodyFactory = new BuilderBasedFactory<Body>(bodyBuilders);
	}
	
	private static void inicializaGravityLawsBuilders()
	{
		ArrayList<Builder<GravityLaws>> gravityLawsBuilders = new ArrayList<>();
		gravityLawsBuilders.add(new NewtonUniversalGravitationBuilder());
		gravityLawsBuilders.add(new FallingToCenterGravityBuilder());
		gravityLawsBuilders.add(new NoGravityBuilder());
		_gravityLawsFactory = new BuilderBasedFactory<GravityLaws>(gravityLawsBuilders);
	}

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseDeltaTimeOption(line);
			parseGravityLawsOption(line);
			parseOutFileOption(line);
			parseStepOption(line);
			parseModeOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		// help
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message.").build());

		// input file
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Bodies JSON input file.").build());

		//mode 
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().desc("Execution Mode. Possible values: ’batch’(Batch mode), "
				+ "'gui' (Graphical User Interface mode). Default value: 'batch'.").build());
		
		// delta-time
		cmdLineOptions.addOption(Option.builder("dt").longOpt("delta-time").hasArg()
				.desc("A double representing actual time, in seconds, per simulation step. Default value: "
						+ _dtimeDefaultValue + ".")
				.build());
			
		//o
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Output file, where output is written. Defaultvalue: the standard output.").build());
		
		//s

		cmdLineOptions.addOption(Option.builder("s").longOpt("steps").hasArg().desc("An integer representing the number ofsimulation steps. Default value: 150.").build());
		
		// gravity laws -- there is a workaround to make it work even when
		// _gravityLawsFactory is null. 
		//
		String gravityLawsValues = "N/A";
		String defaultGravityLawsValue = "N/A";
		if (_gravityLawsFactory != null) {
			gravityLawsValues = "";
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gravityLawsValues.length() > 0) {
					gravityLawsValues = gravityLawsValues + ", ";
				}
				gravityLawsValues = gravityLawsValues + "'" + fe.getString("type") + "' (" + fe.getString("desc") + ")";
			}
			defaultGravityLawsValue = _gravityLawsFactory.getInfo().get(0).getString("type");
		}
		cmdLineOptions.addOption(Option.builder("gl").longOpt("gravity-laws").hasArg()
				.desc("Gravity laws to be used in the simulator. Possible values: " + gravityLawsValues
						+ ". Default value: '" + defaultGravityLawsValue + "'.")
				.build());

		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
	}
	
	private static void parseModeOption (CommandLine line) throws ParseException
	{
		if(line.hasOption("m"))
		{
			if(line.getOptionValue("m").equalsIgnoreCase("batch")) startBatch = true;
			else
			{
				startBatch = false;
			}
		}
		else
		{
			startBatch = true;
			System.out.println("Default value : 'batch'");
		}
	}
	
	private static void parseOutFileOption(CommandLine line)  {
		_outFile = line.getOptionValue("o");
	}
	
	private static void parseStepOption(CommandLine line) throws ParseException
	{
		try 
		{
			if (line.getOptionValue("s")!= null)
			{
				_steps=Integer.parseInt(line.getOptionValue("s"));
				if (_steps < 0)
				{
					throw new ParseException("Incorrect Steps");
				}
			}
			else
			{
				_steps = 150;
				System.out.println("No params at steps, default : 150");
			}
			
		}
		catch(NumberFormatException ex)
		{
			throw new ParseException("Incorrect steps param");
		}
	}
	
	

	private static void parseDeltaTimeOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("dt", _dtimeDefaultValue.toString());
		try {
			_dtime = Double.parseDouble(dt);
			assert (_dtime > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid delta-time value: " + dt);
		}
	}

	private static void parseGravityLawsOption(CommandLine line) throws ParseException {

		// this line is just a work around to make it work even when _gravityLawsFactory
		// is null, you can remove it when've defined _gravityLawsFactory
		if (_gravityLawsFactory == null)
			return;

		String gl = line.getOptionValue("gl");
		if (gl != null) {
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gl.equals(fe.getString("type"))) {
					_gravityLawsInfo = fe;
					break;
				}
			}
			if (_gravityLawsInfo == null) {
				throw new ParseException("Invalid gravity laws: " + gl);
			}
		} else {
			_gravityLawsInfo = _gravityLawsFactory.getInfo().get(0);
		}
	}

	private static void startBatchMode() throws Exception {
		// create and connect components, then start the simulator
		try
		{
			if (_inFile == null) {
				throw new ParseException("An input file of bodies is required");
			}
			
			FileOutputStream fo = null;
			GravityLaws gravityLaws = _gravityLawsFactory.createInstance(_gravityLawsInfo);
		
			PhysicsSimulator sim = new PhysicsSimulator(_dtime, gravityLaws);
			Controller ctrl = new Controller(_bodyFactory,sim, _gravityLawsFactory );
			ctrl.loadBodies(new FileInputStream(_inFile));
			if (_outFile != null) fo = new FileOutputStream(_outFile); 
			ctrl.run(_steps, fo);
		}
		catch(ParseException e)
		{
			System.err.println("Something went wrong in Batch mode ...");
		}
	}
	
	private static void startGUIMode() throws Exception
	{
		try 
		{
			GravityLaws gravityLaws = _gravityLawsFactory.createInstance(_gravityLawsInfo);
		
			PhysicsSimulator sim = new PhysicsSimulator(_dtime, gravityLaws);
			Controller ctrl = new Controller(_bodyFactory,sim, _gravityLawsFactory);
			if(_inFile != null) ctrl.loadBodies(new FileInputStream(_inFile));
		
			SwingUtilities.invokeAndWait(new Runnable() 
			{
				@Override
				public void run() 
				{
					new MainWindow(ctrl);
				}
				
				
			});
		}
		catch (Exception ex)
		{
			System.err.println("Something went wrong in GUI mode ...");
		}
	}
	
	private static void start(String[] args) throws Exception {
		try
		{
			
		parseArgs(args);
		
		if(startBatch) {
			startBatchMode();
			}
		else
			{
			 startGUIMode();
			}
		
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static void main(String[] args) {
		try {
			init();
			start(args);
			System.out.println("Done!");
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}
