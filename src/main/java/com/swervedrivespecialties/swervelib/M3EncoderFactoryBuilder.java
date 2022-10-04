package com.swervedrivespecialties.swervelib;

//import com.swervedrivespecialties.swervelib.AbsoluteEncoder;
//import com.swervedrivespecialties.swervelib.AbsoluteEncoderFactory;

//import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;

public class M3EncoderFactoryBuilder {
//    private Direction direction = Direction.COUNTER_CLOCKWISE;
//    private int periodMilliseconds = 10;

//    public M3EncoderFactoryBuilder withReadingUpdatePeriod(int periodMilliseconds) {
//        this.periodMilliseconds = periodMilliseconds;
//        return this;
//    }

//    public M3EncoderFactoryBuilder withDirection(Direction direction) {
//        this.direction = direction;
//        return this;
//    }

    public AbsoluteEncoderFactory<M3EncoderAbsoluteConfiguration> build() {
        return configuration -> {
            //CANCoderConfiguration config = new CANCoderConfiguration();
            //config.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
            //config.magnetOffsetDegrees = Math.toDegrees(configuration.getOffset());
            //config.sensorDirection = direction == Direction.CLOCKWISE;

            AnalogInput encoder = new AnalogInput(configuration.getId());
            double offset = configuration.getOffset();

            return new EncoderImplementation(encoder, offset);
        };
    }

    private static class EncoderImplementation implements AbsoluteEncoder {
        private final AnalogInput encoder;
        private final double offset;

        private EncoderImplementation(AnalogInput encoder, double offset) {
            this.encoder = encoder;
            this.offset = offset;
        }

        @Override
        public double getAbsoluteAngle() {
            double angle = (1.0 - encoder.getVoltage() / RobotController.getVoltage5V()) * 2.0 * Math.PI + offset;
            angle %= 2.0 * Math.PI;
            if (angle < 0.0) {
                angle += 2.0 * Math.PI;
            }

            return angle;
        }
    }

    public enum Direction {
        CLOCKWISE,
        COUNTER_CLOCKWISE
    }
}
