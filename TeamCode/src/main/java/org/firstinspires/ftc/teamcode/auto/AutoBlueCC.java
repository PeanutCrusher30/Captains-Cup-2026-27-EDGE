package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutoBlueCC extends OpMode {
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    DcMotor Flywheel;
    CRServo intakeServo;

    ElapsedTime runtime;

    GoBildaPinpointDriver pinpoint;

    int Position = 2; //Based on starting Position: 1 is Red, 2 is Blue

    int Step = 1;

    public void init(){
        DcMotor FL = hardwareMap.get(DcMotor.class, "FL");
        DcMotor FR = hardwareMap.get(DcMotor.class, "FR");
        DcMotor BL = hardwareMap.get(DcMotor.class, "BL");
        DcMotor BR = hardwareMap.get(DcMotor.class, "BR");
        DcMotor Flywheel = hardwareMap.get(DcMotor.class, "Flywheel");
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intakeServo");

        ElapsedTime runtime = new ElapsedTime();


        GoBildaPinpointDriver pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setOffsets(0,17.5, DistanceUnit.CM);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        pinpoint.resetPosAndIMU();
        if (Position == 2) {
            Pose2D startingPosition = new Pose2D(DistanceUnit.CM, 330, 200, AngleUnit.RADIANS, 0);
        }

    }

    public void AutoDriveShootPark(){
        double X = pinpoint.getPosX(DistanceUnit.CM);
        double Y = pinpoint.getPosY(DistanceUnit.CM);
        double heading = pinpoint.getHeading(AngleUnit.RADIANS);

        if (Position == 2) {
            if (Step == 1) {
                while (X != 270) {
                    FL.setPower(0.5);
                    FR.setPower(0.5);
                    BL.setPower(0.5);
                    BR.setPower(0.5);}

                if (X == 270) {
                    FL.setPower(0);
                    FR.setPower(0);
                    BL.setPower(0);
                    BR.setPower(0);
                    Step += 1;} }

            if (Step == 2) {
                while (Y != 280) {
                    FL.setPower(0.5);
                    FR.setPower(-0.5);
                    BL.setPower(-0.5);
                    BR.setPower(0.5);}

                if (Y == 280) {
                    FL.setPower(0);
                    FR.setPower(0);
                    BL.setPower(0);
                    BR.setPower(0);
                    Step += 1;}}

            if (Step == 3) {
                while (X != 210) {
                    FL.setPower(0.5);
                    FR.setPower(0.5);
                    BL.setPower(0.5);
                    BR.setPower(0.5);}

                if (X == 210) {
                    FL.setPower(0);
                    FR.setPower(0);
                    BL.setPower(0);
                    BR.setPower(0);
                    Step += 1;}}

            if (Step == 4) {
                while (heading != Math.toRadians(270)) {
                    FL.setPower(-0.5);
                    FR.setPower(0.5);
                    BL.setPower(-0.5);
                    BR.setPower(0.5);}

                if (heading == Math.toRadians(270)) {
                    FL.setPower(0);
                    FR.setPower(0);
                    BL.setPower(0);
                    BR.setPower(0);
                    runtime.reset();
                    Step += 1;
                }}

            if (Step == 5) {
                while (runtime.seconds() <= 7) {
                    Flywheel.setPower(1);
                    intakeServo.setPower(.25);
                }

                if (runtime.seconds() >= 7) {
                    Flywheel.setPower(0);
                    intakeServo.setPower(0);
                    Step += 1;
                }
            }

            if (Step == 6) {
                while (X != 270) {
                    FL.setPower(0.5);
                    FR.setPower(-0.5);
                    BL.setPower(-0.5);
                    BR.setPower(0.5);}

                if (X == 270) {
                    FL.setPower(0);
                    FR.setPower(0);
                    BL.setPower(0);
                    BR.setPower(0);
                    Step += 1;}}

            if (Step == 7) {
                while (Y != 100) {
                    FL.setPower(-0.5);
                    FR.setPower(-0.5);
                    BL.setPower(-0.5);
                    BR.setPower(-0.5);}

                if (Y == 100) {
                    FL.setPower(0);
                    FR.setPower(0);
                    BL.setPower(0);
                    BR.setPower(0);
                    Step += 1;}}

            if (Step == 8) {
                while (X != 330) {
                    FL.setPower(0.5);
                    FR.setPower(-0.5);
                    BL.setPower(-0.5);
                    BR.setPower(0.5);}

                if (X == 330) {
                    FL.setPower(0);
                    FR.setPower(0);
                    BL.setPower(0);
                    BR.setPower(0);
                    Step += 1;}}
        }
    }

    public void loop(){
        AutoDriveShootPark();
        pinpoint.update();
    }

}