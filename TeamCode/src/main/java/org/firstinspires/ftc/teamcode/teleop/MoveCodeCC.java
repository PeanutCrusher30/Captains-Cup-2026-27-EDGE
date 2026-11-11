package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

// Moving:
@TeleOp (name="MoveCodeCC")
public class MoveCodeCC extends OpMode {
    private DcMotor FL;
    private DcMotor BL;
    private DcMotor FR;
    private DcMotor BR;
    //other motors and servos
    private DcMotor flywheel;
    private DcMotor intakeMotor;
    private CRServo intakeServo;
//    private static final double S
//        HOOT_POWER = 1.0; // Tune from 0.0 to 1.0 on the robot.

    @Override
    public void init() {
         BL = hardwareMap.get(DcMotor.class, "fl");
         FR = hardwareMap.get(DcMotor.class, "fr");
         BL = hardwareMap.get(DcMotor.class, "bl");
         BR = hardwareMap.get(DcMotor.class, "br");

         //initialize intake servo
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");

        //initialize flywheel
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        flywheel.setPower(0);
        flywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setPower(0);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void Drive(){
        float LeftStick_x = gamepad1.left_stick_x;
        float LeftStick_y = gamepad1.left_stick_y;
        float RightStick_x = gamepad1.right_stick_x;
//        float LeftTrigger = gamepad1.left_trigger;
//        boolean A = gamepad1.a;
//        boolean B = gamepad1.b;
//        boolean Up = gamepad1.dpad_up;
//        boolean Left = gamepad1.dpad_left;
//        boolean right = gamepad1.dpad_right;
        boolean Right_Bumber = gamepad1.right_bumper;

        FL.setPower(LeftStick_x + LeftStick_y + RightStick_x);
        FR.setPower(-LeftStick_x + LeftStick_y - RightStick_x);
        BL.setPower(LeftStick_x + LeftStick_y + RightStick_x);
        BR.setPower(-LeftStick_x + LeftStick_y - RightStick_x);
    }

    public void shoot(){
        if (Right_Bumper) {
            flywheel.setPower(1.0);
            intakeServo.setPower(.25);
        } else {
            flywheel.setPower(0);
        }
    }
    public void Intake() {
        if (gamepad1.x) {
            intakeMotor.setPower(1.0);
            intakeServo.setPower(.25);
        } else {
            intakeMotor.setPower(0);
        }

        intakeMotor.setPower(0);
    }

    float LeftStick_x;
    float LeftStick_y;
    float RightStick_x;



    //    boolean Up;
//    boolean A;
//    boolean B;
//    boolean Left;
//    boolean Right;
    boolean Right_Bumper;

    public MoveCodeCC(DcMotor fl) {

    }

    public void loop(){
        Drive();
        shoot();
        Intake();
    }

}
