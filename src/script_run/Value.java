package script_run;

/**
 * @author Elisha
 */
public class Value
{
	public ValueType type;
	public Object value;

	public Value(ValueType type,Object value)
	{
		this.type = type;
		this.value = value;
	}

	public Value (Value other)
	{
		value = other.value;
		type = other.type;
	}
	public Value()
	{
		value = new Object();
		type = ValueType.Null;
	}
}
