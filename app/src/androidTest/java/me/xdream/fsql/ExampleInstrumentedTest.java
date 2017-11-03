package me.xdream.fsql;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import me.xdream.fsql.services.DataBaseServices;
import me.xdream.fsql.vo.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    //@Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("me.xdream.fsql", appContext.getPackageName());
    }
    //@Test
    public void testSaveDba(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        Person p=new Person(1,"Peter",1);
        DataBaseServices dataBaseServices=new DataBaseServices(appContext);
        dataBaseServices.save(p);
    }
    @Test
    public void testReadDba(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseServices dataBaseServices=new DataBaseServices(appContext);
        Person person=dataBaseServices.findById(1);
        assertNotNull(person);
        Log.i("DBA", "testReadDba: "+person.toString());
    }
}
