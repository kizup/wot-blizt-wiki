package ru.kizup.wotblitzhelper.business.common_info;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.data.repositories.common_info.ICommonInfoRepository;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoUIModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoUIModelInteractorTest {

    private ICommonInfoRepository mCommonInfoRepository;
    private CommonInfoInteractor mCommonInfoInteractor;

    @Before
    public void beforeEachTest() {
        mCommonInfoRepository = mock(ICommonInfoRepository.class);
        mCommonInfoInteractor = new CommonInfoInteractor(mCommonInfoRepository, mock(ResponseValidator.class));
    }

    @Test
    public void getCommonInfo_success() {
        when(mCommonInfoRepository.getCommonInfo()).thenReturn(Single.fromCallable(() -> {
            HashMap<String, String> languages = new HashMap<>();
            languages.put("ru", "Russian");
            languages.put("en", "English");

            HashMap<String, String> vehicleTypes = new HashMap<>();
            vehicleTypes.put("heavyTank", "Heavy Tank");
            vehicleTypes.put("mediumTank", "Medium Tank");

            HashMap<String, String> vehicleNations = new HashMap<>();
            vehicleNations.put("ussr", "USSR");
            vehicleNations.put("usa", "USA");
            return new CommonInfoDataModel(1513779369L, languages, vehicleTypes, vehicleNations, "4.5.0.1");
        }));
//        when(mCommonInfoInteractor.getCommonInfo()).thenReturn(Single.fromCallable(() ->
//                new CommonInfoUIModel("1.2.3.4",
//                        new Date(System.currentTimeMillis()),
//                        asList("ru", "en"),
//                        asList(new VehicleTypeUIModel("1", "One"), new VehicleTypeUIModel("2", "Two"))))
//        );

        TestObserver<CommonInfoUIModel> testSubscriber = TestObserver.create();
        mCommonInfoInteractor.getCommonInfo().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertComplete();
        CommonInfoUIModel info = testSubscriber.values().get(0);
        assertThat(info.getGameVersion()).isEqualTo("4.5.0.1");
        assertThat(info.getUpdatedAt().getTime()).isEqualTo(1513779369);
    }

    @Test
    public void getCommonInfo_error() {
        when(mCommonInfoRepository.getCommonInfo()).thenReturn(Single.error(CommonInfoLoadException::new));
        TestObserver<CommonInfoUIModel> testSubscriber = TestObserver.create();
        mCommonInfoInteractor.getCommonInfo().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(CommonInfoLoadException.class);
    }

}
