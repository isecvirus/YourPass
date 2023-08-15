package org.virus.yourpass;

import com.formdev.flatlaf.ui.FlatComboBoxUI;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author kiwif https://repo1.maven.org/maven2/com/formdev/flatlaf/3.1.1/
 */
public class YourPass extends JFrame {

    public void initalize() {
        IconFontSwing.register(FontAwesome.getIconFont());
        getRootPane().putClientProperty("JRootPane.titleBarShowIconify", true);
        getRootPane().putClientProperty("JRootPane.titleBarShowMaximize", true);
        getRootPane().putClientProperty("JRootPane.titleBarShowClose", true);
        UIManager.put("Panel.arc", 0);

//        UIManager.put("PasswordField.revealIconColor", color(255));
        UIManager.put("PasswordField.capsLockIconColor", color(139));
        UIManager.put("TextField.selectionBackground", color(100));

        UIManager.put("ScrollPane.smoothScrolling", true);
        UIManager.put("showButtons", true);

        UIManager.put("ToolBar.dockingBackground", color(190));
        UIManager.put("ToolBar.floatingBackground", color(70));
        UIManager.put("ToolBar.gripColor", color(255));

        UIManager.put("Button.background", color(50));
        UIManager.put("Button.disabledBackground", color(20));
        UIManager.put("Button.default.background", color(50));
        UIManager.put("Button.hoverBackground", color(35));
        UIManager.put("Button.pressedBackground", color(50));
//        UIManager.put("Button.selectedBackground", color(30));

        UIManager.put("Button.toolbar.hoverBackground", color(25));
        UIManager.put("Button.toolbar.pressedBackground", color(50));

//        UIManager.put("CheckBox.icon.selectedBackground", color(150));
//        UIManager.put("CheckBox.icon.checkmarkColor", color(0));
//        UIManager.put("TitlePane.unifiedBackground", true);
//        UIManager.put("TitlePane.background", color(50));
//        UIManager.put("ToolBar.background", color(50));
//        UIManager.put("TableHeader.background", color(30));
//        UIManager.put("Table.background", color(30));
//        UIManager.put("TableHeader.separatorColor", color(150));
        UIManager.put("TableHeader.bottomSeparatorColor", color(150));
        UIManager.put("Table.selectionBackground", color(150));
//        UIManager.put("Table.gridColor", color(150));
        UIManager.put("Table.sortIconColor", color(255));
        UIManager.put("TableHeader.sortIconPosition", "left");
        UIManager.put("Table.showHorizontalLines", true);
//        UIManager.put("Table.showVerticalLines", true);

        UIManager.put("TitlePane.foreground", color(200));
        UIManager.put("TitlePane.background", color(30)); // shows as #673d01
//        UIManager.put("TitlePane.inactiveBackground", Color.decode("#090700"));

        UIManager.put("TitlePane.borderColor", color(255));

        UIManager.put("Spinner.buttonSeparatorColor", color(100));
        UIManager.put("Component.arc", 0); // roundness of some elements

        UIManager.put("TextComponent.arc", 10); // roundness of some elements
        UIManager.put("JButton.buttonType", "roundRect"); // roundness of some elements

        UIManager.put("TitlePane.closeHoverBackground", color(150));
        UIManager.put("TitlePane.closePressedBackground", color(200));

//        UIManager.put("MenuBar.foreground", Color.lightGray);
        UIManager.put("MenuBar.hoverBackground", color(75));
//        UIManager.put("MenuBar.selectionForeground", Color.decode("#ffffff"));
        UIManager.put("MenuBar.selectionBackground", color(175));
        UIManager.put("MenuBar.underlineSelectionColor", color(175));
        UIManager.put("MenuItem.underlineSelectionColor", color(175));
        UIManager.put("MenuItem.underlineSelectionBackground", color(35));
        UIManager.put("MenuItem.underlineSelectionCheckBackground", color(75));
        UIManager.put("MenuItem.selectionBackground", color(200));
        UIManager.put("CheckBoxMenuItem.icon.checkmarkColor", color(255));
        UIManager.put("Menu.icon.arrowColor", color(255));

        UIManager.put("MenuBar.borderColor", color(175));
        UIManager.put("MenuItem.selectionType", "underline");
        UIManager.put("MenuBar.underlineSelectionBackground", color(35));

        UIManager.put("Component.arrowType", "chevron"); // triangle
        UIManager.put("Component.focusWidth", 0);
        UIManager.put("Button.focusWidth", 0);
        UIManager.put("Button.arc", 0);

        UIManager.put("TitlePane.centerTitle", true); // center window title 

        UIManager.put("Button.borderColor", color(50));
        UIManager.put("Button.hoverBorderColor", color(100));
        UIManager.put("Button.toolbar.focusColor", color(150));

        UIManager.put("Component.borderColor", color(50));
        UIManager.put("Component.focusedBorderColor", color(255));
        UIManager.put("Table.cellFocusColor", color(35));
        UIManager.put("Table.focusCellBackground", color(65));


        String[] keywords_columns = {"Keyword", "Length"};
        String[] passwords_columns = {"Password", "Length"};

        keywordsList = new ArrayList<String>();
        passwordsList = new ArrayList<String>();
        addToolbar = new JToolBar();
        keyword_filed = new JTextField();
        searchKeywordsToolbar = new JToolBar();
        searchKeywordsField = new JTextField();
        tablesPanel = new JPanel();
        splitPane = new JSplitPane();
        keywordsScrollPane = new JScrollPane();
        passwordsScrollPane = new JScrollPane();
        
        searchPasswordsToolbar = new JToolBar();
        searchPasswordsField = new JTextField();
        windowMenu = new JMenuBar();
        importMenu = new JMenu();
        exportMenu = new JMenu();
        
        addKeywordListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_keyword();
            }
        };
        typingKeywordListener = new KeyAdapter() {
            public void keyPress(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                validate_keyword_availability();
            }
        };

        setTitle("YourPass");
        setUndecorated(false);

        addToolbar.setBorder(null);
        addToolbar.setRollover(true);
        addToolbar.setPreferredSize(new java.awt.Dimension(64, 22));

        keyword_trailing_toolbar = new JToolBar();
        keyword_leading_toolbar = new JToolBar();

        keyword_menu_btn = new JButton(icon(FontAwesome.COG, color(255)));
        keyword_menu_btn.setAlignmentY(0.8f);
        keyword_menu_btn.setFocusable(false);
        keyword_popup = new JPopupMenu();

        override_existence = new JCheckBoxMenuItem("Override existence");
        override_existence.setToolTipText("Add keyword even if it's in the list");
        override_existence.addActionListener(e -> {
            validate_keyword_availability();
        });

        keyword_remove_spaces = new JCheckBoxMenuItem("Remove space");
        keyword_remove_spaces.setToolTipText("Remove spaces from keyword");
        keyword_remove_spaces.addActionListener(e -> {
            update_keyword_length();
        });
        keyword_remove_spaces.setSelected(true);

        keyword_popup.add(override_existence);
        keyword_popup.add(keyword_remove_spaces);

        keyword_menu_btn.addActionListener(e -> {
            keyword_popup.show(keyword_menu_btn, (-(int) override_existence.getPreferredSize().getWidth()) + keyword_menu_btn.getWidth() - 2, keyword_menu_btn.getHeight() - 1);
        });

        keyword_length = new JButton("0");
        keyword_length.setAlignmentY(0.8f);
        keyword_length.setFocusable(false);
        
        keyword_trailing_toolbar.addSeparator();
        keyword_trailing_toolbar.add(keyword_menu_btn);
        keyword_trailing_toolbar.add(keyword_length);

        keyword_availability = new JButton(icon(FontAwesome.MINUS_CIRCLE, Color.gray));
        keyword_availability.addActionListener(e->{add_keyword();});
        keyword_availability.setToolTipText("Spaces are ignored");
        keyword_availability.setAlignmentY(0.8f);
        
        keyword_leading_toolbar.add(keyword_availability);
        keyword_leading_toolbar.addSeparator();
        
        keyword_filed.setFont(new java.awt.Font("Segoe UI", 0, 14));
        keyword_filed.setText("{[x] Override existence} [add] [<Length>:xxx]");
        keyword_filed.putClientProperty("JTextField.leadingComponent", keyword_leading_toolbar);
        keyword_filed.putClientProperty("JTextField.trailingComponent", keyword_trailing_toolbar);
        keyword_filed.putClientProperty("JTextField.showClearButton", true);
        keyword_filed.putClientProperty("JTextField.placeholderText", "Add keyword..");
        keyword_filed.addActionListener(addKeywordListener);
        keyword_filed.addKeyListener(typingKeywordListener);
        addToolbar.add(keyword_filed);

        keyword_filed.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update_keyword_length();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update_keyword_length();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update_keyword_length();
            }
        });

        searchKeywordsToolbar.setBorder(null);
        searchKeywordsToolbar.setRollover(true);

        searchKeywordsField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        searchKeywordsField.setText("[Filter By(keyword,length)] Search keyword..                                [mix] [import] [export] [Edit] [Copy] [Delete]");
        searchKeywordsField.putClientProperty("JTextField.showClearButton", true);
        searchKeywordsField.putClientProperty("JTextField.placeholderText", "Search keyword..");
        searchKeywordsToolbar.add(searchKeywordsField);

        splitPane.setDividerLocation(0.25);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(true);

        keywords_table_model = new DefaultTableModel(0, keywords_columns.length);
        keywords_table_model.setColumnIdentifiers(keywords_columns);
        keywords_table_renderer = new DefaultTableCellRenderer();
        keywords_table_renderer.setHorizontalAlignment(SwingConstants.CENTER);

        keywordsTable = new JTable(keywords_table_model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        ;
        };
        setTableRenderer(keywordsTable, keywords_table_renderer);

        keywordsTable.setAutoCreateRowSorter(true);

        keywordsTable.setFillsViewportHeight(true);
        keywordsTable.setRowHeight(rows_height);
        keywordsTable.setSelectionMode(0);
        keywordsTable.setShowGrid(true);
        keywordsTable.getTableHeader().setReorderingAllowed(false);
        keywordsTable.setModel(keywordsTable.getModel());
        keywordsScrollPane.setViewportView(keywordsTable);

        splitPane.setTopComponent(keywordsScrollPane);
        
        passwords_table_model = new DefaultTableModel(0, passwords_columns.length);
        passwords_table_model.setColumnIdentifiers(passwords_columns);
        passwords_table_renderer = new DefaultTableCellRenderer();
        passwords_table_renderer.setHorizontalAlignment(SwingConstants.CENTER);
        passwordsTable = new JTable(passwords_table_model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            };
        };
        setTableRenderer(passwordsTable, passwords_table_renderer);
        
        passwordsTable.setAutoCreateRowSorter(true);
        passwordsTable.setFillsViewportHeight(true);
        passwordsTable.setRowHeight(rows_height);
        passwordsTable.setSelectionMode(0);
        passwordsTable.setShowGrid(true);
        passwordsTable.getTableHeader().setReorderingAllowed(false);
        passwordsScrollPane.setViewportView(passwordsTable);

        splitPane.setRightComponent(passwordsScrollPane);

        GroupLayout tablesPanelLayout = new GroupLayout(tablesPanel);
        tablesPanel.setLayout(tablesPanelLayout);
        tablesPanelLayout.setHorizontalGroup(
                tablesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(tablesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(tablesPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)))
        );
        tablesPanelLayout.setVerticalGroup(
                tablesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 784, Short.MAX_VALUE)
                        .addGroup(tablesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(tablesPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)))
        );

        searchPasswordsToolbar.setBorder(null);
        searchPasswordsToolbar.setRollover(true);

        searchPasswordsField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        searchPasswordsField.setText("[Filter By(keyword,length)] Search keyword..                                 [import] [export] [Edit] [Copy] [Delete]");
        searchPasswordsField.putClientProperty("JTextField.showClearButton", true);
        searchPasswordsField.putClientProperty("JTextField.placeholderText", "Search password..");
        searchPasswordsToolbar.add(searchPasswordsField);

        importMenu.setText("Import");
        windowMenu.add(importMenu);

        exportMenu.setText("Export");
        windowMenu.add(exportMenu);

        setJMenuBar(windowMenu);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(tablesPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(addToolbar, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(searchPasswordsToolbar, GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                                        .addComponent(searchKeywordsToolbar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addToolbar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchKeywordsToolbar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tablesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchPasswordsToolbar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        setPreferredSize(new Dimension(dim.width / 2, dim.height / 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JCheckBoxMenuItem override_existence, keyword_remove_spaces;
    private JButton keyword_availability, keyword_menu_btn, keyword_length;
    private List<String> passwordsList, keywordsList;
    private JTextField keyword_filed, searchKeywordsField, searchPasswordsField;
    private JToolBar addToolbar, searchKeywordsToolbar, searchPasswordsToolbar, keyword_trailing_toolbar, keyword_leading_toolbar;
    private JPopupMenu keyword_popup;
    private JMenu exportMenu, importMenu;
    private JScrollPane keywordsScrollPane, passwordsScrollPane;
    private JTable keywordsTable, passwordsTable;
    private DefaultTableModel keywords_table_model, passwords_table_model;
    private DefaultTableCellRenderer keywords_table_renderer, passwords_table_renderer;
    private JSplitPane splitPane;
    private JPanel tablesPanel;
    private JMenuBar windowMenu;
    private Action addKeywordListener;
    private KeyAdapter typingKeywordListener;
    private int rows_height = 50;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ulafe) {
        }
        new YourPass().initalize();

    }

    private Icon icon(IconCode type, Object... args) {
        List<Object> argList = Arrays.asList(args);
        Color color = Color.decode("#434e60");
        int size = 15;
        int maxArgs = 2; // size, color, etc..

        if (args.length >= maxArgs - 1) {
            for (Object arg : argList) {
                if (arg instanceof Integer) {
                    size = (int) arg;
                } else if (arg instanceof Color) {
                    color = (Color) arg;
                }
            }
        }

        return IconFontSwing.buildIcon(type, size, color);
    }

    private static Color color(int alpha) {
        Color color = Color.decode("#CE1D3C");
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    private String get_keyword() {
        String keyword = keyword_filed.getText();
        if (keyword_remove_spaces.isSelected()) {
            keyword = keyword.replace(" ", "");
        }
        return keyword;
    }

    private void validate_keyword_availability() {
        String keyword = get_keyword();
        if (!keyword.isEmpty()) {
            if (!keywordsList.contains(keyword) || override_existence.isSelected()) { // available
                keyword_availability.setIcon(icon(FontAwesome.CHECK_CIRCLE, Color.decode("#00ff00")));
            } else {
                keyword_availability.setIcon(icon(FontAwesome.TIMES_CIRCLE, Color.decode("#ff0000")));
            }
        } else {
            keyword_availability.setIcon(icon(FontAwesome.MINUS_CIRCLE, Color.gray));
        }
    }

    private void update_keyword_length() {
        String keyword = get_keyword();
        if (!keyword.isEmpty()) {
            keyword_length.setText((String.valueOf(keyword.length())));
        } else {
            keyword_length.setText("0");
        }
    }

    public void auto_remove_keyword_spaces() {
        if (keyword_remove_spaces.isSelected()) {
            try {
                int cur = keyword_filed.getCaretPosition(); // current/curser
                int sel_start = keyword_filed.getSelectionStart();
                int sel_end = keyword_filed.getSelectionEnd();
                if (sel_end > get_keyword().length()) {
                    sel_end = get_keyword().length() - 1;
                }

                keyword_filed.setText(get_keyword());
                keyword_filed.setCaretPosition(cur);
                keyword_filed.setSelectionStart(sel_start);
                keyword_filed.setSelectionEnd(sel_end);
            } catch (IllegalArgumentException iae) {
            }
        }
    }

    private void add_keyword() {
        String keyword = get_keyword();
        if ((!keywordsList.contains(keyword) || override_existence.isSelected()) & !keyword.replace(" ", "").isEmpty() ) {
            keywordsList.add(keyword);

            String[] data = {keyword, String.valueOf(keyword.length())};
            keywords_table_model.addRow(data);
            
            keyword_filed.setText("");
            update_keyword_length();
            validate_keyword_availability();
        }
    }

    public void setTableRenderer(JTable table, DefaultTableCellRenderer renderer) {
        for (TableColumn column : Collections.list(table.getColumnModel().getColumns())) {
            column.setCellRenderer(renderer);
        }
    }

    private interface dim { // dimension

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension ScreenSize = toolkit.getScreenSize();
        int width = ScreenSize.width;
        int height = ScreenSize.height;
    }
}
