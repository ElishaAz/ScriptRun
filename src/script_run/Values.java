package script_run;

import script_run.lines.ILine;

/**
 * @author Elisha
 */
public class Values
{
	static final char[] digits = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
	static final char point = '.';
	static final char negative = '.';
	static final char[] operators = {'-', '+', '%', '/', '*'}; // in order of importance
	static final char[] intOnlyOperators = {'%'};
	static final String[] boolOperators = {"||", "&&", "!"}; // in order of importance

	public Value valueOf(String exp)
	{
		Value ans = new Value();

		if (exp.contains("\"") || exp.contains("\'"))
		{

		} else if (exp.contains("true") || exp.contains("false"))
		{

		} else
		{
			numVal(exp);
		}

		return ans;
	}

	Value numVal(String exp)
	{
		Value value = new Value();
		if (!isNum(exp))
		{
			//error
			return null;
		}
		if (exp.contains("("))
		{
			int sum = 0;
			int index = 0;
			for (int i = exp.indexOf('('); i < exp.length(); i++)
			{
				if (exp.charAt(i) == '(')
				{
					if (sum == 0)
					{
						index = i;
					}
					sum++;
				} else if (exp.charAt(i) == ')')
				{
					sum--;
					if (sum < 0)
					{
						// error
						return null;
					}
					if (sum == 0)
					{
						Value val = numVal(exp.substring(index + 1, i));

						exp = exp.substring(0, index) + val.value.toString() + exp.substring(i + 1);
						i = index + 1 + val.value.toString().length();
					}
				}
			}
		}
		for (char op : operators)
		{
			int index = exp.indexOf(op);
			if (op == negative)
			{
				int i = index - 1;
				for (; i >= 0 && exp.charAt(i) == ' '; i++) ;
				if (isOperator(exp.charAt(i)))
					continue;
			}
			if (index == 0 || index == operators.length)
			{
				//error
				return null;
			}
			if (index != -1)
			{
				numVal(exp.substring(0, index));
				numVal(exp.substring(index + 1));
			}
		}

		return value;
	}

	Value applyOpNum(Value a, char op, Value b)
	{
		if (!(a.value instanceof Number) || (b.value instanceof Number))
		{
			return null;
		}
		Value val = new Value();
		Number aNum = (Number) a.value;
		Number bNum = (Number) a.value;
		Integer i = 0;
		switch (op)
		{
			case '%':
				val.value = aNum.intValue()%bNum.intValue();
				break;
		}

		return val;
	}

	boolean isNum(String exp)
	{
		boolean ans = true;

		for (char c : exp.toCharArray())
		{
			if (!(isDigit(c) || isOperator(c) || c == ' ') || c == '(' || c == ')')
			{
				return false;
			}
		}

		return ans;
	}

	boolean isWhole(String exp)
	{
		boolean ans = true;
		int i = 0;

		if (exp.charAt(0) == negative)
			i = 1;

		for (; i < exp.length(); i++)
		{
			if (!isDigit(exp.charAt(i)))
			{
				return false;
			}
		}

		return ans;
	}

	boolean isFloat(String exp)
	{
		boolean ans = true;

		for (char c : exp.toCharArray())
		{
			if (!isDigit(c))
			{
				return false;
			}
		}

		return ans;
	}

	boolean isDigit(char c)
	{
		boolean ans = false;

		for (char digit : digits)
		{
			if (c == digit)
			{
				ans = true;
				break;
			}
		}

		return ans;
	}

	boolean isOperator(char c)
	{
		boolean ans = false;

		for (char operator : operators)
		{
			if (c == operator)
			{
				ans = true;
				break;
			}
		}

		return ans;
	}

	boolean isBoolOperator(String c)
	{
		boolean ans = false;

		for (String operator : boolOperators)
		{
			if (c == operator)
			{
				ans = true;
				break;
			}
		}

		return ans;
	}
}
