import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

// 新增的父类，用于管理通用订单属性和方法
class OrderManager {
    // 可以在这里添加通用的订单属性，例如订单编号
    private int orderId;

    public OrderManager(int orderId) {
        this.orderId = orderId;
    }

    // 可以添加通用的订单方法，例如获取订单编号
    public int getOrderId() {
        return orderId;
    }
}

// 新增的CustomPizza类
class CustomPizza {
    // 配料
    private String toppings;
    // 价格
    private double price;

    // 构造方法
    public CustomPizza(String toppings, double price) {
        this.toppings = toppings;
        this.price = price;
    }

    // Getter和Setter方法
    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "CustomPizza [toppings=" + toppings + ", price=" + price + "]";
    }
}

// 处理订单的类，继承自OrderManager
class HandleOrders extends OrderManager {
    // 存储定制披萨的ArrayList
    private ArrayList<CustomPizza> customPizzas = new ArrayList<>();
    // 订单日志栈
    private OrderLogs orderLogs = new OrderLogs();
    // 订单队列
    private Queue<String> orderQueue = new ArrayDeque<>();

    // 构造方法
    public HandleOrders(int orderId) {
        super(orderId);
    }

    // 处理定制披萨订单的方法
    public void handleCustomPizzaOrder() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder customPizzaToppings = new StringBuilder();
        // 选择配料逻辑，可复用PizzaShop中的选择逻辑
        System.out.println("Please pick any toppings for your custom pizza:");
        String[] toppingChoices = scanner.nextLine().split(" ");
        for (String choice : toppingChoices) {
            customPizzaToppings.append(choice).append(" ");
        }
        System.out.println("Enter the price of the custom pizza:");
        double customPizzaPrice = scanner.nextDouble();
        CustomPizza customPizza = new CustomPizza(customPizzaToppings.toString(), customPizzaPrice);
        customPizzas.add(customPizza);
        // 记录订单日志
        orderLogs.addOrderLog("Custom pizza ordered: " + customPizza);
        // 添加订单到队列
        orderQueue.add("Custom pizza ordered: " + customPizza);
    }

    // 显示定制披萨列表的方法
    public void displayCustomPizzas() {
        for (CustomPizza pizza : customPizzas) {
            System.out.println(pizza);
        }
    }

    // 处理订单日志的方法
    public void handleOrderLogs() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose what you want to do with order logs:");
            System.out.println("1. Display all the logs");
            System.out.println("2. Display the most recent logs");
            System.out.println("3. Remove a log entry");
            System.out.println("4. Remove all log entries");
            System.out.println("5. Check if the log is completely empty");
            System.out.println("Enter your choice (1 – 5)");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    orderLogs.displayOrderLogs();
                    break;
                case 2:
                    orderLogs.mostRecentLogEntry();
                    break;
                case 3:
                    orderLogs.getOrderLog();
                    break;
                case 4:
                    orderLogs.removeAllLogEntries();
                    break;
                case 5:
                    orderLogs.orderLogsEmpty();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            System.out.println("Do you want to continue? (Y/N)");
            if (scanner.next().equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    // 处理订单队列的方法
    public void handleOrderQueue() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose what you want to do with the order queue:");
            System.out.println("1. Display all orders in the queue");
            System.out.println("2. Remove the next order from the queue");
            System.out.println("3. Check if the queue is empty");
            System.out.println("Enter your choice (1 – 3)");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayOrderQueue();
                    break;
                case 2:
                    removeNextOrderFromQueue();
                    break;
                case 3:
                    checkQueueEmpty();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            System.out.println("Do you want to continue? (Y/N)");
            if (scanner.next().equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    // 显示订单队列的方法
    private void displayOrderQueue() {
        for (String order : orderQueue) {
            System.out.println(order);
        }
    }

    // 从订单队列移除下一个订单的方法
    private void removeNextOrderFromQueue() {
        if (!orderQueue.isEmpty()) {
            System.out.println("Removed order: " + orderQueue.poll());
        } else {
            System.out.println("The order queue is empty.");
        }
    }

    // 检查订单队列是否为空的方法
    private void checkQueueEmpty() {
        if (orderQueue.isEmpty()) {
            System.out.println("The order queue is empty.");
        } else {
            System.out.println("The order queue is not empty.");
        }
    }

    // 重写toString方法，展示订单处理相关信息
    @Override
    public String toString() {
        return "HandleOrders [orderId=" + getOrderId() + ", customPizzas=" + customPizzas + ", orderLogs=" + orderLogs + ", orderQueue=" + orderQueue + "]";
    }
}

// 订单日志类
class OrderLogs {
    // 订单日志栈
    private ArrayDeque<String> orderLogs = new ArrayDeque<>();

    // 构造方法
    public OrderLogs() {
        // 初始化栈
    }

    // 添加订单日志的方法
    public void addOrderLog(String log) {
        orderLogs.push(log);
    }

    // 显示所有订单日志的方法
    public void displayOrderLogs() {
        for (String log : orderLogs) {
            System.out.println(log);
        }
    }

    // 显示最近订单日志的方法
    private void mostRecentLogEntry() {
        if (!orderLogs.isEmpty()) {
            System.out.println("Most recent log entry: " + orderLogs.peek());
        } else {
            System.out.println("The order logs are empty.");
        }
    }

    // 获取并移除最上面订单日志的方法
    private String getOrderLog() {
        if (!orderLogs.isEmpty()) {
            return orderLogs.pop();
        } else {
            System.out.println("The order logs are empty.");
            return null;
        }
    }

    // 移除所有订单日志的方法
    private void removeAllLogEntries() {
        orderLogs.clear();
        System.out.println("All order log entries removed.");
    }

    // 检查订单日志是否为空的方法
    private boolean orderLogsEmpty() {
        if (orderLogs.isEmpty()) {
            System.out.println("The log is completely empty");
            return true;
        } else {
            System.out.println("The log is not completely empty");
            return false;
        }
    }
}

// 主类
public class Main {
    public static void main(String[] args) {
        HandleOrders handleOrders = new HandleOrders(1);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Place a custom pizza order");
            System.out.println("2. Display custom pizzas");
            System.out.println("3. Handle order logs");
            System.out.println("4. Handle order queue");
            System.out.println("5. Exit");
            System.out.println("Enter your choice (1 – 5)");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleOrders.handleCustomPizzaOrder();
                    break;
                case 2:
                    handleOrders.displayCustomPizzas();
                    break;
                case 3:
                    handleOrders.handleOrderLogs();
                    break;
                case 4:
                    handleOrders.handleOrderQueue();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}