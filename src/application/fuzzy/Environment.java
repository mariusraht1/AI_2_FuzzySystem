package application.fuzzy;

public class Environment {
    // TODO: Classification of stock, demand and order
    private static Environment instance;

    public static Environment getInstance() {
	if (instance == null) {
	    instance = new Environment();
	}

	return instance;
    }

    public static void setInstance(Environment instance) {
	Environment.instance = instance;
    }

    private Environment() {
    };

}
