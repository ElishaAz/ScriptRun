/**
 * @author Elisha
 */

package script_run.lines;

import script_run.ErrorType;
import script_run.Value;

import java.util.Dictionary;

public abstract class ILine
{
	public String errMessage;
	public ErrorType errorType;

	/**
	 * Executable line.
	 *
	 * @param args the arguments.
	 * @param vars the variable list.
	 * @return next line to execute (-1 for next line, -2 error with errMessage and errorType, -3 no arguments).
	 */
	public abstract int run(String[] args, Dictionary<String, Value> vars);

	/**
	 * @param s argument string to get string from
	 * @return the string. null if not a string.
	 */
	public static String getString(String s)
	{
		StringBuilder sb = new StringBuilder();

		s = s.trim();

		if (s.indexOf('\"') != 0)
			return null;

		for (int i = 1; i < s.length() - 1; i++)
		{
			if (s.charAt(i) == '\\')
			{
				i++;
				if (i != s.length() - 1) ;
				{
					sb = addSpecialChar(s.charAt(i), sb);
				}
			} else if (s.charAt(i) == '\"')
			{
				return null;
			}
		}

		return sb.toString();
	}

	static StringBuilder addSpecialChar(char c, StringBuilder sb)
	{
		switch (c)
		{
			case '\"':
				sb.append('\"');
				break;
			case '\n':
				sb.append('\n');
				break;
			case '\t':
				sb.append('\t');
				break;
			case '\'':
				sb.append('\'');
				break;
			case '\\':
				sb.append('\\');
				break;
			case '\b':
				sb.append('\b');
				break;
			case '\r':
				sb.append('\r');
				break;
			case '\f':
				sb.append('\f');
				break;

		}
		return sb;
	}

	boolean isVar(String s, Dictionary<String, Value> vars)
	{
		return vars.get(s) != null;
	}
}
