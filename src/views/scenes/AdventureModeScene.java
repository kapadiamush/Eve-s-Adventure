package views.scenes;

import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import models.Coordinate;
import models.campaign.World;
import models.gridobjects.creatures.Creature;
import views.ATopMenu;
import views.MainApp;
import views.grid.Cols;
import views.grid.GridWorld;
import views.grid.Rows;
import views.karel.KarelTable;
import views.tabs.GameTabs;
import views.tips.Objective;
import views.tips.ProTips;
import controllers.ButtonHandlers;
import controllers.Interpreter;

public final class AdventureModeScene extends Scene {

	private static World world;

	public static GridWorld gridWorld;
	
	 public static GameTabs gametabs = null;
	 public static ProTips protips = null;
	 public static KarelTable karelTable = null;
	 public static Objective objective = null;
	
	private static Interpreter interpreter;
	
	public static ToggleButton Eve = new ToggleButton("Eve!");

	public static Button PLAY, PAUSE, FORWARD, RESET;
	
	public static ImageView imagePlay = new ImageView(new Image(AdventureModeScene.class.getResourceAsStream("/Images/PlayButton.png")));
	
	public static ImageView imagePause = new ImageView(new Image(AdventureModeScene.class.getResourceAsStream("/Images/pause.png")));
	
	public static final class AdventureModePane extends GridPane {

		public static AdventureModePane instanceOfAdventurePane = null;

		//private ImageView imageBack = new ImageView(new Image(
		//		"./Images/ArrowLeft.png"));
		private ImageView imageRight = new ImageView(new Image(AdventureModeScene.class.getResourceAsStream("/Images/ArrowRight.png")));
		private ImageView imageReset = new ImageView(new Image(AdventureModeScene.class.getResourceAsStream("/Images/ResetButton.png")));

		private AdventureModePane() {
			this.getStylesheets().add(AdventureModeScene.class.getResource("/sandbox_style.css").toExternalForm());

			//buttonSetup();
			
			PLAY = new Button();
			PAUSE = new Button();
			FORWARD = new Button();
			RESET = new Button();
			
			PAUSE.setGraphic(imagePause);
			FORWARD.setGraphic(imageRight);
			PLAY.setGraphic(imagePlay);
			RESET.setGraphic(imageReset);
			
			PAUSE.setTooltip(new Tooltip("PAUSE"));
			FORWARD.setTooltip(new Tooltip("FORWARD"));
			RESET.setTooltip(new Tooltip("RESET"));
			PLAY.setTooltip(new Tooltip("PLAY"));
			
			PAUSE.setDisable(true);
			FORWARD.setDisable(true);

			PAUSE.setOnAction(ButtonHandlers::PAUSE_BUTTON_HANDLER);
			FORWARD.setOnAction(ButtonHandlers::FORWARD_BUTTON_HANDLER);
			PLAY.setOnAction(ButtonHandlers::PLAY_BUTTON_HANDLER);
			RESET.setOnAction(ButtonHandlers::RESET_BUTTON_HANDLER);

			GridPane.setFillWidth(PAUSE, true);
			GridPane.setHalignment(PAUSE, HPos.CENTER);
			GridPane.setFillWidth(FORWARD, true);
			GridPane.setHalignment(FORWARD, HPos.CENTER);
			GridPane.setFillWidth(PLAY, true);
			GridPane.setHalignment(PLAY, HPos.CENTER);
			GridPane.setFillWidth(RESET, true);
			GridPane.setHalignment(RESET, HPos.CENTER);

			ATopMenu topMenu = ATopMenu.getInstance();
			gametabs = GameTabs.getInstance();
			gametabs.setId("gametabs");
			protips = ProTips.getInstance();
			karelTable = KarelTable.getInstance();
			protips.setId("protips");
			if(gridWorld == null){
				gridWorld = GridWorld.getInstance();
			}
			objective = Objective.getInstance();

			Rows rows = Rows.getInstance();
			Cols cols = Cols.getInstance();

			this.add(topMenu, 0, 0, 7, 1);
			this.add(PAUSE, 2, 1, 2, 1);
			this.add(FORWARD, 4, 1);
			this.add(PLAY, 5, 1);
			this.add(RESET, 6, 1);
			//this.add(gametabs, 0, 1, 1, 3);
			//this.add(karelTable, 1, 1, 1, 3);
			this.add(cols, 3, 2, 4, 1);
			this.add(rows, 2, 3, 1, 2);
			//this.add(gridWorld, 3, 3, 4, 2);
			//this.add(protips, 0, 4, 2, 1);

			GridPane.setHalignment(rows, HPos.RIGHT);

			ColumnConstraints column1 = new ColumnConstraints();
			column1.setPercentWidth(32);
			ColumnConstraints column2 = new ColumnConstraints();
			column2.setPercentWidth(36);
			ColumnConstraints column3 = new ColumnConstraints();
			column3.setPercentWidth(4);
			ColumnConstraints column4 = new ColumnConstraints();
			column4.setPercentWidth(4);
			ColumnConstraints column5 = new ColumnConstraints();
			column5.setPercentWidth(8);
			ColumnConstraints column6 = new ColumnConstraints();
			column6.setPercentWidth(8);
			ColumnConstraints column7 = new ColumnConstraints();
			column7.setPercentWidth(8);
			this.getColumnConstraints().addAll(column1, column2, column3,
					column4, column5, column6, column7);

			RowConstraints row1 = new RowConstraints();
			row1.setPercentHeight(3);
			RowConstraints row2 = new RowConstraints();
			row2.setPercentHeight(5);
			RowConstraints row3 = new RowConstraints();
			row3.setPercentHeight(4);
			RowConstraints row4 = new RowConstraints();
			row4.setPercentHeight(78);
			RowConstraints row5 = new RowConstraints();
			row5.setPercentHeight(10);
			/*
			 * RowConstraints row6 = new RowConstraints();
			 * row6.setPercentHeight(20.5); RowConstraints row7 = new
			 * RowConstraints(); row7.setPercentHeight(20.5); RowConstraints
			 * row8 = new RowConstraints(); row8.setPercentHeight(10);
			 */
			this.getRowConstraints().addAll(row1, row2, row3, row4, row5);

			// BACKEND-FRONTEND Integration
			if(world != null){
				world = new World("SandboxWorld", 10, 5);
				GridWorld.getInstance().setWorld(world);
				world.addCreature(new Creature("Eve", new Coordinate(2, 2)));
				// world.printWorld();
				// Coordinate coordEve = new Coordinate(1,1);
				// Creature CreatureEve = new Creature("Eve", coordEve);
				// world.addCreature(CreatureEve);
				// world.printWorld();
				//
				// Label Eve = new Label("Eve!");
				//GridPane.setHalignment(Eve, HPos.CENTER);
				System.out.println("AdventureMode Scene things");
				//GridWorld.gridButtons[2][2].setText("Eve!");
				Eve.setVisible(true);
			}
			
			//
			// //world.moveEveEast();
			// world.moveEve();
			// gridWorld.getChildren().remove(Eve);
			// gridWorld.add(Eve, CreatureEve.getX(), CreatureEve.getY());
			// world.printWorld();
			//
			// //world.moveEveSouth();
			// world.moveEve();
			// gridWorld.getChildren().remove(Eve);
			// gridWorld.add(Eve, CreatureEve.getX(), CreatureEve.getY());
			// world.printWorld();

			// this.setPadding(new Insets(0, 5, 5, 5));

			// this.setGridLinesVisible(true);

		}

		private void buttonSetup() {
			PAUSE = new Button();
			
			PAUSE.setGraphic(imagePause);
			FORWARD.setGraphic(imageRight);
			PLAY.setGraphic(imagePlay);
			RESET.setGraphic(imageReset);
			
			PAUSE.setTooltip(new Tooltip("PAUSE"));
			FORWARD.setTooltip(new Tooltip("FORWARD"));
			RESET.setTooltip(new Tooltip("RESET"));
			PLAY.setTooltip(new Tooltip("PLAY"));
			
			PAUSE.setDisable(true);
			FORWARD.setDisable(true);

			PAUSE.setOnAction(ButtonHandlers::PAUSE_BUTTON_HANDLER);
			FORWARD.setOnAction(ButtonHandlers::FORWARD_BUTTON_HANDLER);
			PLAY.setOnAction(ButtonHandlers::PLAY_BUTTON_HANDLER);
			RESET.setOnAction(ButtonHandlers::RESET_BUTTON_HANDLER);
		}

		public static AdventureModePane getInstance() {
			if(instanceOfAdventurePane == null){
				instanceOfAdventurePane = new AdventureModePane();
			}
			return instanceOfAdventurePane;
		}
	}

	private static AdventureModeScene instanceOfAdventureModeScene = null; 
	private AdventureModeScene(Parent arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}

	public static void setWorld(World world1) {
		world = world1;
		gridWorld = GridWorld.getInstance();
		gridWorld.setWorld(world1);
	}

	public void setGridWorld(GridWorld gridworld1) {
		gridWorld = gridworld1;
	}

	public World getWorld() {
		return world;
	}

	public GridWorld getGridWorld() {
		return gridWorld;
	}

	public static AdventureModeScene getInstance() {
		if(AdventureModeScene.instanceOfAdventureModeScene == null){
			instanceOfAdventureModeScene = new AdventureModeScene(
					AdventureModePane.getInstance(), MainApp.WINDOW_WIDTH,
					MainApp.WINDOW_HEIGHT);
		}
		return instanceOfAdventureModeScene;
	}
	public static Interpreter getInterpreter() {
		return interpreter;
	}

	public static void setInterpreter(Interpreter interpreter) {
		AdventureModeScene.interpreter = interpreter;
	}
}
