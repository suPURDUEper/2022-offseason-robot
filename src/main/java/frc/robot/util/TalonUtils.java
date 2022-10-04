
package frc.robot.util;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

public class TalonUtils {

    public static final int MAX_PERIOD_MS = 255;
    public static void setStatusFramePeriodFollower(BaseTalon talon) {
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, MAX_PERIOD_MS);
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, MAX_PERIOD_MS);
    }

    public static TalonFXConfiguration getDefaultTalonConfig() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.nominalOutputForward = 0.0;
        config.nominalOutputReverse = 0.0;
        config.peakOutputForward = 1.0;
        config.peakOutputReverse = -1.0;
        config.neutralDeadband = 0.04;
        return config;
    }

    
}