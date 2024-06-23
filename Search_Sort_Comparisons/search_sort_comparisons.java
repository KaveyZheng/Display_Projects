import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class search_sort_comparison extends BinarySearchTree
{
	private static int width = 700;
	private static int width2 = 350;
	private static int height = 350;
	private static String sortFileName;
	private static String searchFileName;
	private static boolean enableSearchButton = false;
	private static int[] sortArr;
	private static int[] searchArr;

	private static int[] sortedValues;
	private static TreeSet<Integer> treesetValues = new TreeSet<>();
	private static HashSet<Integer> hashsetValues = new HashSet<>();
	private static PriorityQueue<Integer> priorityQueueValues = new PriorityQueue<>();
	private static ArrayList<Integer> arrayListValues = new ArrayList<>();
	private static ArrayList<Integer> sortedArrayListValues = new ArrayList<>();
	private static BinarySearchTree bst = new BinarySearchTree();
	private static int[] unsortedValues;

	private static boolean readInts;
	private static boolean readBST;
	private static boolean readTreeSet;
	private static boolean readPriorityQueue;
	private static boolean readHashSet;
	private static boolean readArrayList;
	private static boolean readSortedArrayList;
	private static boolean readArray;

	private static JButton sortIntsButton = new JButton("sort ints");
	private static JLabel sortIntsLabel = new JLabel("no result");
	private static JButton addToBSTButton = new JButton("add to BST");
	private static JLabel addToBSTLabel = new JLabel("no result");
	private static JButton addToTreeSetButton = new JButton("add to treeset");
	private static JLabel addToTreeSetLabel = new JLabel("no result");
	private static JButton addToPriorityQueueButton = new JButton("add to priority queue");
	private static JLabel addToPriorityQueueLabel = new JLabel("no result");
	private static JButton addToHashSetButton = new JButton("add to hashset");
	private static JLabel addToHashSetLabel = new JLabel("no result");
	private static JButton addToArrayListButton = new JButton("add to arraylist");
	private static JLabel addToArrayListLabel = new JLabel("no result");
	private static JButton addToSortedArrayListButton = new JButton("add to sorted arraylist");
	private static JLabel addToSortedArrayListLabel = new JLabel("no result");
	private static JButton addToArrayButton = new JButton("add to array");
	private static JLabel addToArrayLabel = new JLabel("no result");
	
	private static JButton searchSortedIntsButton = new JButton("search sorted ints");
	private static JLabel searchSortedIntsLabel = new JLabel("no result");
	private static JButton searchBSTButton = new JButton("search BST");
	private static JLabel searchBSTLabel = new JLabel("no result");
	private static JButton searchTreeSetButton = new JButton("search treeset");
	private static JLabel searchTreeSetLabel = new JLabel("no result");
	private static JButton searchPriorityQueueButton = new JButton("search priority queue");
	private static JLabel searchPriorityQueueLabel = new JLabel("no result");
	private static JButton searchHashSetButton = new JButton("search hashset");
	private static JLabel searchHashSetLabel = new JLabel("no result");
	private static JButton searchArrayListButton = new JButton("search arraylist");
	private static JLabel searchArrayListLabel = new JLabel("no result");
	private static JButton searchSortedArrayListButton = new JButton("search sorted arraylist");
	private static JLabel searchSortedArrayListLabel = new JLabel("no result");
	private static JButton searchArrayButton = new JButton("search array");
	private static JLabel searchArrayLabel = new JLabel("no result");
	
	public static void main(String[] args)
	{
		if (args.length < 2)
		{
			System.out.println("format is: \"input sort file name\" \"output search file name\"");
			System.exit(0);
		}
		sortFileName = args[0];
		searchFileName = args[1];
	


		// create the window and specify the size and what to do when the window is closed
		JFrame f = new JFrame();
		f.setPreferredSize(new Dimension(width, height));
		f.setMinimumSize(new Dimension(width, height));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the menu bar
		JMenuBar menuBar = new JMenuBar();
		
		// create the menu
		JMenu fileMenu = new JMenu("File");
		
		// create the menu item for the menu
		JMenuItem fileExit = new JMenuItem("Exit");
		JMenuItem fileReadSort = new JMenuItem("Read Sort File");
		JMenuItem fileReadSearch = new JMenuItem("Read Search File");
		
		// add the menu to the menu bar
		menuBar.add(fileMenu);
		
		// add the menu item to the menus
		fileMenu.add(fileReadSort);
		fileMenu.add(fileReadSearch);
		fileMenu.add(fileExit);
		
		// create the panel to hold the buttons
		JPanel leftButtonPanel = new JPanel();
		leftButtonPanel.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 255, 255), 2));
		leftButtonPanel.setPreferredSize(new Dimension(width2, height));
		leftButtonPanel.setMinimumSize(new Dimension(width2, height));
		GridBagLayout leftGridBagLayout = new GridBagLayout();
		leftButtonPanel.setLayout(leftGridBagLayout);

		JPanel rightButtonPanel = new JPanel();
		rightButtonPanel.setBorder(new javax.swing.border.LineBorder(new Color(0, 255, 255, 255), 2));
		rightButtonPanel.setPreferredSize(new Dimension(width2, height));
		rightButtonPanel.setMinimumSize(new Dimension(width2, height));
		GridBagLayout rightGridBagLayout = new GridBagLayout();
		rightButtonPanel.setLayout(rightGridBagLayout);

		GridBagConstraints leftButtonPanelConstraints = new GridBagConstraints();
		GridBagConstraints rightButtonPanelConstraints = new GridBagConstraints();

		leftButtonPanelConstraints.weightx = 1;
		leftButtonPanelConstraints.weighty = 1;
		leftButtonPanelConstraints.fill = GridBagConstraints.NONE;
		// leftButtonPanelConstraints.anchor = GridBagConstraints.LINE_START;

		// Add sortIntsButton
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 0;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(sortIntsButton, leftButtonPanelConstraints);

		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 0;
		leftButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(sortIntsLabel, leftButtonPanelConstraints);

		// Add addToBSTButton
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 1;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToBSTButton, leftButtonPanelConstraints);

		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 1;
		leftButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToBSTLabel, leftButtonPanelConstraints);

		// Add addToTreeSetButton
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 2;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToTreeSetButton, leftButtonPanelConstraints);

		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 2;
		leftButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToTreeSetLabel, leftButtonPanelConstraints);

		// Add addToPriorityQueueButton
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 3;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToPriorityQueueButton, leftButtonPanelConstraints);

		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 3;
		leftButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToPriorityQueueLabel, leftButtonPanelConstraints);

		// Add addToHashSetButton
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 4;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToHashSetButton, leftButtonPanelConstraints);

		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 4;
		leftButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToHashSetLabel, leftButtonPanelConstraints);

		// Add addToArrayListButton
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 5;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToArrayListButton, leftButtonPanelConstraints);

		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 5;
		leftButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToArrayListLabel, leftButtonPanelConstraints);

		// Add addToSortedArrayListButton
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 6;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToSortedArrayListButton, leftButtonPanelConstraints);

		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 6;
		leftButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToSortedArrayListLabel, leftButtonPanelConstraints);

		// Add addToArrayButton
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 7;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToArrayButton, leftButtonPanelConstraints);

		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 7;
		leftButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToArrayLabel, leftButtonPanelConstraints);

		leftButtonPanel.add(sortIntsButton);
		leftButtonPanel.add(sortIntsLabel);
		leftButtonPanel.add(addToBSTButton);
		leftButtonPanel.add(addToBSTLabel);
		leftButtonPanel.add(addToTreeSetButton);
		leftButtonPanel.add(addToTreeSetLabel);
		leftButtonPanel.add(addToPriorityQueueButton);
		leftButtonPanel.add(addToPriorityQueueLabel);
		leftButtonPanel.add(addToHashSetButton);
		leftButtonPanel.add(addToHashSetLabel);
		leftButtonPanel.add(addToArrayListButton);
		leftButtonPanel.add(addToArrayListLabel);
		leftButtonPanel.add(addToSortedArrayListButton);
		leftButtonPanel.add(addToSortedArrayListLabel);
		leftButtonPanel.add(addToArrayButton);
		leftButtonPanel.add(addToArrayLabel);

		sortIntsButton.setEnabled(false);
		addToBSTButton.setEnabled(false);
		addToTreeSetButton.setEnabled(false);
		addToPriorityQueueButton.setEnabled(false);
		addToHashSetButton.setEnabled(false);
		addToArrayListButton.setEnabled(false);
		addToSortedArrayListButton.setEnabled(false);
		addToArrayButton.setEnabled(false);

		rightButtonPanelConstraints.weightx = 1;
		rightButtonPanelConstraints.weighty = 1;
		rightButtonPanelConstraints.fill = GridBagConstraints.NONE;
		//rightButtonPanelConstraints.anchor = GridBagConstraints.LINE_START;


		// Add searchIntsButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 0;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchSortedIntsButton, rightButtonPanelConstraints);

		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 0;
		rightButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchSortedIntsLabel, rightButtonPanelConstraints);

		// Add searchBSTButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 1;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchBSTButton, rightButtonPanelConstraints);

		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 1;
		rightButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchBSTLabel, rightButtonPanelConstraints);

		// Add searchTreeSetButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 2;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchTreeSetButton, rightButtonPanelConstraints);

		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 2;
		rightButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchTreeSetLabel, rightButtonPanelConstraints);

		// Add searchPriorityQueueButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 3;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchPriorityQueueButton, rightButtonPanelConstraints);

		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 3;
		rightButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchPriorityQueueLabel, rightButtonPanelConstraints);

		// Add searchHashSetButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 4;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchHashSetButton, rightButtonPanelConstraints);

		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 4;
		rightButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchHashSetLabel, rightButtonPanelConstraints);

		// Add searchArrayListButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 5;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchArrayListButton, rightButtonPanelConstraints);

		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 5;
		rightButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchArrayListLabel, rightButtonPanelConstraints);

		// Add searchSortedArrayListButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 6;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchSortedArrayListButton, rightButtonPanelConstraints);

		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 6;
		rightButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchSortedArrayListLabel, rightButtonPanelConstraints);

		// Add searchArrayButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 7;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchArrayButton, rightButtonPanelConstraints);

		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 7;
		rightButtonPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchArrayLabel, rightButtonPanelConstraints);

		rightButtonPanel.add(searchSortedIntsButton);
		rightButtonPanel.add(searchSortedIntsLabel);
		rightButtonPanel.add(searchBSTButton);
		rightButtonPanel.add(searchBSTLabel);
		rightButtonPanel.add(searchTreeSetButton);
		rightButtonPanel.add(searchTreeSetLabel);
		rightButtonPanel.add(searchPriorityQueueButton);
		rightButtonPanel.add(searchPriorityQueueLabel);
		rightButtonPanel.add(searchHashSetButton);
		rightButtonPanel.add(searchHashSetLabel);
		rightButtonPanel.add(searchArrayButton);
		rightButtonPanel.add(searchArrayLabel);
		rightButtonPanel.add(searchSortedArrayListButton);
		rightButtonPanel.add(searchSortedArrayListLabel);
		rightButtonPanel.add(searchArrayButton);
		rightButtonPanel.add(searchArrayLabel);

		searchSortedIntsButton.setEnabled(false);
		searchBSTButton.setEnabled(false);
		searchTreeSetButton.setEnabled(false);
		searchPriorityQueueButton.setEnabled(false);
		searchHashSetButton.setEnabled(false);
		searchArrayListButton.setEnabled(false);
		searchSortedArrayListButton.setEnabled(false);
		searchArrayButton.setEnabled(false);

		JPanel mainButtonPanel = new JPanel();
		mainButtonPanel.setBorder(new javax.swing.border.LineBorder(new Color(0, 255, 0, 255), 2));
		BoxLayout boxLayout = new BoxLayout(mainButtonPanel, BoxLayout.X_AXIS);
		mainButtonPanel.setLayout(boxLayout);
		mainButtonPanel.add(leftButtonPanel);
		mainButtonPanel.add(rightButtonPanel);
								
		// create the panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new javax.swing.border.LineBorder(new Color(255, 0, 0, 255), 2));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(menuBar, BorderLayout.NORTH);
		mainPanel.add(mainButtonPanel, BorderLayout.CENTER);
		
		// create the action listener for the menu item
		MenuItemActionListener fileExitMenuItemActionListener = new MenuItemActionListener(fileExit);
		MenuItemActionListener fileReadSortMenuItemActionListener = new MenuItemActionListener(fileReadSort);
		MenuItemActionListener fileReadSearchMenuItemActionListener = new MenuItemActionListener(fileReadSearch);
		
		// add the action listener to the menu item
		fileExit.addActionListener(fileExitMenuItemActionListener);
		fileReadSort.addActionListener(fileReadSortMenuItemActionListener);
		fileReadSearch.addActionListener(fileReadSearchMenuItemActionListener);

		// create action listener for buttons 
		ButtonActionListener sortIntsActionListener = new ButtonActionListener(sortIntsButton);
		ButtonActionListener addToBSTActionListener = new ButtonActionListener(addToBSTButton);
		ButtonActionListener addToTreeSetActionListener = new ButtonActionListener(addToTreeSetButton);
		ButtonActionListener addToPriorityQueueActionListener = new ButtonActionListener(addToPriorityQueueButton);
		ButtonActionListener addToHashSetActionListener = new ButtonActionListener(addToHashSetButton);
		ButtonActionListener addToArrayListActionListener = new ButtonActionListener(addToArrayListButton);
		ButtonActionListener addToSortedArrayListActionListener = new ButtonActionListener(addToSortedArrayListButton);
		ButtonActionListener addToArrayActionListener = new ButtonActionListener(addToArrayButton);

		ButtonActionListener searchSortedIntsActionListener = new ButtonActionListener(searchSortedIntsButton);
		ButtonActionListener searchBSTActionListener = new ButtonActionListener(searchBSTButton);
		ButtonActionListener searchTreeSetActionListener = new ButtonActionListener(searchTreeSetButton);
		ButtonActionListener searchPriorityQueueActionListener = new ButtonActionListener(searchPriorityQueueButton);
		ButtonActionListener searchHashSetActionListener = new ButtonActionListener(searchHashSetButton);
		ButtonActionListener searchArrayListActionListener = new ButtonActionListener(searchArrayListButton);
		ButtonActionListener searchSortedArrayListActionListener = new ButtonActionListener(searchSortedArrayListButton);
		ButtonActionListener searchArrayActionListener = new ButtonActionListener(searchArrayButton);

		// add action listener to buttons
		sortIntsButton.addActionListener(sortIntsActionListener);
		addToBSTButton.addActionListener(addToBSTActionListener);
		addToTreeSetButton.addActionListener(addToTreeSetActionListener);
		addToPriorityQueueButton.addActionListener(addToPriorityQueueActionListener);
		addToHashSetButton.addActionListener(addToHashSetActionListener);
		addToArrayListButton.addActionListener(addToArrayListActionListener);
		addToSortedArrayListButton.addActionListener(addToSortedArrayListActionListener);
		addToArrayButton.addActionListener(addToArrayActionListener);

		searchSortedIntsButton.addActionListener(searchSortedIntsActionListener);
		searchBSTButton.addActionListener(searchBSTActionListener);
		searchTreeSetButton.addActionListener(searchTreeSetActionListener);
		searchPriorityQueueButton.addActionListener(searchPriorityQueueActionListener);
		searchHashSetButton.addActionListener(searchHashSetActionListener);
		searchArrayListButton.addActionListener(searchArrayListActionListener);
		searchSortedArrayListButton.addActionListener(searchSortedArrayListActionListener);
		searchArrayButton.addActionListener(searchArrayActionListener);
		
		f.setContentPane(mainPanel);
		f.validate();
		f.setVisible(true);
	}
	
	// action listener for the buttons
	static class ButtonActionListener implements java.awt.event.ActionListener
	{
		// the button associated with the action listener, so that we can
		// share this one class with multiple buttons
		private javax.swing.JButton b;
		
		ButtonActionListener(javax.swing.JButton b) { this.b = b; }
		
		public void actionPerformed(java.awt.event.ActionEvent e) 
		{ 
			System.out.println("action performed on " + b.getText() + " button"); 
			String event = b.getText().toLowerCase();
			long time = System.currentTimeMillis();
			switch (event)
			{
				case "sort ints":
					selectionSort();
					sortIntsLabel.setText(System.currentTimeMillis() - time + "ms");
					if (enableSearchButton) searchSortedIntsButton.setEnabled(true);
					readInts = true;
					break;
				case "add to bst":
					addToBinarySearchTree();
					addToBSTLabel.setText(System.currentTimeMillis() - time + "ms");
					if (enableSearchButton) searchBSTButton.setEnabled(true);
					readBST = true;
					break;
				case "add to treeset":
					addToTreeSet();
					addToTreeSetLabel.setText(System.currentTimeMillis() - time + "ms");
					if (enableSearchButton) searchTreeSetButton.setEnabled(true);
					readTreeSet = true;
					break;
				case "add to priority queue":
					addToPriorityQueue();
					addToPriorityQueueLabel.setText(System.currentTimeMillis() - time + "ms");
					if (enableSearchButton) searchPriorityQueueButton.setEnabled(true);
					readPriorityQueue = true;
					break;
				case "add to hashset":
					addToHashSet();
					addToHashSetLabel.setText(System.currentTimeMillis() - time + "ms");
					if (enableSearchButton) searchHashSetButton.setEnabled(true);
					readHashSet = true;
					break;
				case "add to arraylist":
					addToArrayList();
					addToArrayListLabel.setText(System.currentTimeMillis() - time + "ms");
					if (enableSearchButton) searchArrayListButton.setEnabled(true);
					readArrayList = true;
					break;
				case "add to sorted arraylist":
					addToSortedArrayList();
					addToSortedArrayListLabel.setText(System.currentTimeMillis() - time + "ms");
					if (enableSearchButton) searchSortedArrayListButton.setEnabled(true);
					readSortedArrayList = true;
					break;
				case "add to array":
					addToArray();
					addToArrayLabel.setText(System.currentTimeMillis() - time + "ms");
					if (enableSearchButton) searchArrayButton.setEnabled(true);
					readArray = true;
					break;

				case "search sorted ints":
					searchSortedIntsLabel.setText(searchSortedInts() + " / " + (System.currentTimeMillis() - time) + "ms");
					break;
				case "search bst":
					searchBSTLabel.setText(searchBinarySearchTree() + " / " + (System.currentTimeMillis() - time) + "ms");
					break;
				case "search treeset":
					searchTreeSetLabel.setText(searchTreeSet() + " / " + (System.currentTimeMillis() - time) + "ms");
					break;
				case "search priority queue":
					searchPriorityQueueLabel.setText(searchPriorityQueue() + " / " + (System.currentTimeMillis() - time) + "ms");
					break;
				case "search hashset":
					searchHashSetLabel.setText(searchHashSet() + " / " + (System.currentTimeMillis() - time) + "ms");
					break;
				case "search arraylist":
					searchArrayListLabel.setText(searchArrayList() + " / " + (System.currentTimeMillis() - time) + "ms");
					break;
				case "search sorted arraylist":
					searchSortedArrayListLabel.setText(searchSortedArrayList() + " / " + (System.currentTimeMillis() - time) + "ms");
					break;
				case "search array":
					searchArrayLabel.setText(searchArray() + " / " + (System.currentTimeMillis() - time) + "ms");
					break;
			}
		}

		private static void selectionSort()
		{
			sortedValues = sortArr.clone();
			for (int i = 0; i < sortedValues.length - 1; i++)
			{
				int minIdx = i;
				for (int j = i + 1; j < sortedValues.length; j++)
				{
					if (sortedValues[minIdx] > sortedValues[j]) minIdx = j;
				}
				int temp = sortedValues[i];
				sortedValues[i] = sortedValues[minIdx];
				sortedValues[minIdx] = temp;
			}
		}
		private static void addToBinarySearchTree()
		{
			for (int n : sortArr) { bst.insertNode(new Node(n)); }
		}
		private static void addToTreeSet()
		{
			for (int n : sortArr) { treesetValues.add(n); }
		}
		private static void addToPriorityQueue()
		{
			for (int n : sortArr) { priorityQueueValues.add(n); }
		}
		private static void addToHashSet()
		{
			for (int n : sortArr) { hashsetValues.add(n); }
		}
		private static void addToArrayList()
		{
			for (int n : sortArr) { arrayListValues.add(n); }
		}
		private static void addToSortedArrayList()
		{
			for (int n : sortArr) { sortedArrayListValues.add(n); }
			Collections.sort(sortedArrayListValues);
		}
		private static void addToArray()
		{
			unsortedValues = new int[sortArr.length];
			for (int i = 0; i < unsortedValues.length; i++) { unsortedValues[i] = sortArr[i]; }
		}


		private static boolean binarySearch(int[] arr, int target)
		{
			int top = arr.length - 1;
			int bot = 0;

			while (bot <= top)
			{
				int mid = (top + bot) / 2;
				if (target < arr[mid]) { top = mid - 1; } 
				else if (target > arr[mid]) { bot = mid + 1; }
				else { return true; }
			} 
			return false;
		}
		private static int searchSortedInts()
		{
			int count = 0;
			for (int n : searchArr)
			{
				if (binarySearch(sortedValues, n)) count++;
			}
			return count;
		}
		private static int searchBinarySearchTree()
		{
			int count = 0;
			for (int n : searchArr)
			{
				if (bst.getNode(bst.getRoot(), n) != null) count++;
			}
			return count;
		}
		private static int searchTreeSet()
		{
			int count = 0;
			for (int n : searchArr) { 
				if (treesetValues.contains(n)) count++; 
			}
			return count; 
		}
		private static int searchPriorityQueue()
		{
			int count = 0;
			for (int n : searchArr) { 
				if (priorityQueueValues.contains(n)) count++; 
			}
			return count;
		}
		private static int searchHashSet()
		{
			int count = 0;
			for (int n : searchArr) { 
				if (hashsetValues.contains(n)) count++; 
			}
			return count;
		}
		private static int searchArrayList()
		{
			int count = 0;
			for (int n : searchArr) { 
				if (arrayListValues.contains(n)) count++; 
			}
			return count;
		}
		private static int searchSortedArrayList()
		{
			int count = 0;
			for (int n : searchArr) { 
				if (Collections.binarySearch(sortedArrayListValues, n) > -1) count++; 
			}
			return count; 
		}
		private static int searchArray()
		{
			int count = 0;
			for (int i = 0; i < searchArr.length; i++) 
			{ 
				for (int j = 0; j < unsortedValues.length; j++)
				{
					if (searchArr[i] == unsortedValues[j])
					{
						count++;
						break;
					}
				}
			}
			return count;
		}
	}
	
	// action listener for the menu items
	static class MenuItemActionListener implements java.awt.event.ActionListener
	{
		// the menu item associated with the action listener, so that we can
		// share this one class with multiple menu items
		private javax.swing.JMenuItem m;
		
		MenuItemActionListener(javax.swing.JMenuItem m) { this.m = m; }
		
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
			String event = m.getText().toLowerCase();
			System.out.println("action performed on " + m.getText() + " menu item");
			
			// if exit is selected from the file menu, exit the program
			if( event.equals("exit") ) { System.exit(0); }
			if( event.equals("read sort file") ) 
			{ 
				System.out.println("read sort file");
				readSort(sortFileName);

				sortIntsButton.setEnabled(true);
				addToBSTButton.setEnabled(true);
				addToTreeSetButton.setEnabled(true);
				addToPriorityQueueButton.setEnabled(true);
				addToHashSetButton.setEnabled(true);
				addToArrayListButton.setEnabled(true);
				addToSortedArrayListButton.setEnabled(true);
				addToArrayButton.setEnabled(true);
			}
			if( event.equals("read search file") ) 
			{ 
				System.out.println("read search file");
				readSearch(searchFileName);
				enableSearchButton = true; 

				if (readInts) { searchSortedIntsButton.setEnabled(true); }
				if (readBST) { searchBSTButton.setEnabled(true); }
				if (readTreeSet) { searchTreeSetButton.setEnabled(true); }
				if (readPriorityQueue) { searchPriorityQueueButton.setEnabled(true); }
				if (readHashSet) { searchHashSetButton.setEnabled(true); }
				if (readArrayList) { searchArrayListButton.setEnabled(true); }
				if (readSortedArrayList) { searchSortedArrayListButton.setEnabled(true); }
				if (readArray) { searchArrayButton.setEnabled(true); }
			}
		}
	}
	
	private static void readSort(String file)
	{
		try
		{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			ArrayList<Integer> inputLines = new ArrayList<>();
			String inn;
			while((inn = input.readLine()) != null ) { inputLines.add(Integer.parseInt(inn)); }
			input.close();

			sortArr = new int[inputLines.size()];
			for (int i = 0; i < inputLines.size(); i++) { sortArr[i] = inputLines.get(i); }
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
	private static void readSearch(String file)
	{
		try
		{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			ArrayList<Integer> inputLines = new ArrayList<>();
			String inn;
			while((inn = input.readLine()) != null ) { inputLines.add(Integer.parseInt(inn)); }
			input.close();

			searchArr = new int[inputLines.size()];
			for (int i = 0; i < inputLines.size(); i++) { searchArr[i] = inputLines.get(i); }
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
