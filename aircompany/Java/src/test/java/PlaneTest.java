import entity.model.ClassificationLevel;
import entity.model.ExperimentalType;
import entity.model.MilitaryType;
import entity.plane.ExperimentalPlane;
import entity.plane.MilitaryPlane;
import entity.plane.PassengerPlane;
import entity.plane.Plane;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import run.Runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaneTest {
    private List<PassengerPlane> passengerPlanes;
    private List<MilitaryPlane> militaryPlanes;
    private List<ExperimentalPlane> experimentalPlanes;
    private List<String> expected;

    @BeforeTest
    private void setUp() {
        passengerPlanes = Arrays.asList(
                new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
                new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192));
        militaryPlanes = Arrays.asList(new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER));
        experimentalPlanes = Arrays.asList(new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
                new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalType.VTOL, ClassificationLevel.TOP_SECRET)
        );
        expected = Arrays.asList("Boeing-737", "Boeing-737-800");
    }

    @Test
    public void testGetModel() {
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(passengerPlanes.get(i).getModel(), expected.get(i));
        }
    }

    @Test
    public void testEqualsSelf() {
        Assert.assertEquals(passengerPlanes.get(0), passengerPlanes.get(0));
    }

    @Test
    public void testEqualsIdentical() {
        Assert.assertEquals(experimentalPlanes.get(1), new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalType.VTOL, ClassificationLevel.TOP_SECRET));
    }

    @Test
    public void testNotEqualsSameInstance() {
        Assert.assertNotEquals(passengerPlanes.get(1), passengerPlanes.get(0));
    }

    @Test
    public void testNotEqualsAnotherInstance() {
        Assert.assertNotEquals(militaryPlanes.get(1), passengerPlanes.get(0));
    }

    @Test
    public void testPassengerToString(){
        Assert.assertNotEquals(passengerPlanes.get(0).toString().indexOf("passengersCapacity"), -1);
    }


    @Test
    public void testMilitaryToString(){
        Assert.assertNotEquals(militaryPlanes.get(0).toString().indexOf("type"), -1);
    }

    @Test
    public void testExperimentalToString(){
        Assert.assertNotEquals(experimentalPlanes.get(0).toString().indexOf("type"), -1);
        Assert.assertNotEquals(experimentalPlanes.get(0).toString().indexOf("classificationLevel"), -1);
    }
}
