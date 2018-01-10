package ru.kizup.wotblitzhelper.models.view_vehicle;

/**
 * Created by: dpuzikov on 10.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface VehicleModule {

    int getTier();

    int getWeight();

    String getName();

    int getId();

    Type getType();

    enum Type {
        ENGINE {
            @Override
            public String getImageUrl() {
                return "http://wiki.gcdn.co/images/thumb/2/25/Blitz_vehicleEngine_copy.png/62px-Blitz_vehicleEngine_copy.png";
            }

            @Override
            public String getTypeForRequest() {
                return "vehicleEngine";
            }
        },
        GUN {
            @Override
            public String getImageUrl() {
                return "http://wiki.gcdn.co/images/thumb/7/71/Blitz_vehicleGun_copy.png/62px-Blitz_vehicleGun_copy.png";
            }

            @Override
            public String getTypeForRequest() {
                return "vehicleGun";
            }
        },
        SUSPENSION {
            @Override
            public String getImageUrl() {
                return "http://wiki.gcdn.co/images/thumb/7/78/Blitz_vehicleChassis_copy.png/62px-Blitz_vehicleChassis_copy.png";
            }

            @Override
            public String getTypeForRequest() {
                return "vehicleChassis";
            }
        },
        TURRET {
            @Override
            public String getImageUrl() {
                return "http://wiki.gcdn.co/images/thumb/e/ee/Blitz_vehicleTurret_copy.png/62px-Blitz_vehicleTurret_copy.png";
            }

            @Override
            public String getTypeForRequest() {
                return "vehicleTurret";
            }
        };

        public abstract String getImageUrl();
        public abstract String getTypeForRequest();
    }

}
