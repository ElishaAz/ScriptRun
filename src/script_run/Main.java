/**
 * @author Elisha
 */

package script_run;


import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Scanner;

public class Main
{
	private static String path = "src/abc/abc.txt";
	private static File file;
	private static List<String> lines;
	private static Dictionary<String, Value> vars;

	public static void main(String[] args) throws IOException
	{
		file = new File(path);
		if (!file.exists())
		{
			//noinspection ResultOfMethodCallIgnored
			file.createNewFile();
		}
		lines = getFile(file);
	}

	public static List<String> getFile(File file) throws FileNotFoundException
	{
		List<String> ans = new ArrayList<>();
		Scanner scanner;
		scanner = new Scanner(file);

		while (scanner.hasNextLine())
			ans.add(scanner.nextLine());

		return ans;
	}
}
