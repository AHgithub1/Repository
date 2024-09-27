// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.lang.Object;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final XboxController controller;
  private final JoystickButton button;
  private final CANSparkMax motor;
  private final CANSparkMax motor2;
  private final Timer timer1;
  boolean a;
  boolean b;
  double ly;
  double ry;
  boolean checks;
  double currenttime;
  public ExampleSubsystem() {
    checks = true;
    motor = new CANSparkMax(3, MotorType.kBrushless);
    controller = new XboxController(0);
    button = new JoystickButton(controller, XboxController.Button.kA.value);
    motor2 = new CANSparkMax(4, MotorType.kBrushless);
    motor2.follow(motor);
    timer1 = new Timer();
    timer1.start();
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }
  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    //System.out.println("Hello world\n");
    a = controller.getAButtonPressed();
    
    //b = controller.getAButtonReleased();
    if (a){
      //System.out.println("hello world");
      if (checks){
        //System.out.println("why");
        motor.set(0.5);
        checks = false;
      }else{
        motor.set(0);
        checks = true;
      }
    }
    ly = controller.getLeftY();
    ry = controller.getRightY();
    if (ly == 0 && ry == 0){
      //
    }else if (ly == 0){
      motor.set(2*ry/3);
    }else{
      motor.set(2*ly/3);
    }
    currenttime = timer1.get();
    if (currenttime <= 4.0){
      motor.set(currenttime/5);
    }
    if (currenttime >= 6.0 && currenttime <= 9.9){
      motor.set(1-(currenttime-6)/4);
    }
    if (currenttime >= 10.0){
      timer1.stop();
    }
    // This method will be called once per scheduler run
    //test comment
    //test comment 2
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
