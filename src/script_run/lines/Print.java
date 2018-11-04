/**
 * @author Elisha
 */

package script_run.lines;

import script_run.ErrorType;
import script_run.Value;

import java.util.Dictionary;

public class Print extends ILine
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
		for (String arg : args)
		{
			arg = arg.trim();
			if (arg.indexOf('\"') != 0)
			{
				Object val = vars.get(arg);
				if (val != null)
				{
					System.out.print(val.toString());
				} else
				{
					errorType = ErrorType.Syntax;
					errMessage = "Not a variable";
					return -2;
				}
			} else
			{
				String s = getString(arg);
				if (s != null)
				{
					System.out.print(s);
				}
			}
		}
		System.out.println();
		return -1;
	}
}
