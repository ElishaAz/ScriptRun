package script_run.lines;

import script_run.ErrorType;
import script_run.Value;

import java.util.Dictionary;

/**
 * @author Elisha
 */
public class Variable extends ILine
{
	/**
	 * Executable line.
	 *
	 * @param args the arguments.
	 * @param vars the variable list.
	 * @return next line to execute (-1 for next line, -2 error with errMessage and errorType, -3 no arguments).
	 */
	@Override
	public int run(String[] args, Dictionary<String, Value> vars)
	{
		if (args.length == 0)
			return -3;

		if (isVarType(args[0]))
		{

		} else if (isVar(args[0], vars))
		{

		} else
		{
			errorType = ErrorType.Syntax;
			errMessage = "unknown command " + args[0];
			return -2;
		}

		return -1;
	}

	static final String[] varTypes = {"bool","byte", "char", "short", "int", "float", "double", "long", "String"};

	Object newVar(String type, String value)
	{
		Object ans = new Object();

		switch (type)
		{
			case "bool":
				ans = Boolean.parseBoolean(value);
				break;
			case "byte":
				ans = Byte.parseByte(value);
				break;
			case "char":
				ans = value.trim().charAt(1);
				break;
			case "short":
				ans = Short.parseShort(value);
				break;
			case "int":
				ans = Integer.parseInt(value);
				break;
			case "float":
				ans = Float.parseFloat(value);
				break;
			case "double":
				ans = Double.parseDouble(value);
				break;
			case "long":
				ans = Long.parseLong(value);
				break;
			case "String":
				ans = getString(value);
				break;
		}
		return ans;
	}

	boolean isVarType(String string)
	{
		string.trim();
		boolean ans = false;
		for (String s : varTypes)
		{
			if (string.equals(s))
			{
				ans = true;
				break;
			}
		}
		return ans;
	}
}
