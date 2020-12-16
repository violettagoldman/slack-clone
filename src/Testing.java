import com.srf.routes.RouteItem;
import com.srf.routes.RouteTree;
import org.junit.Test;

public class Testing {

    public Testing(){}

    @Test
    public void testTree(){
        RouteTree rt = new RouteTree(null, "GET");
        rt.insert(new RouteItem("user/getall",null));
        rt.insert(new RouteItem("user/getall2",null));
        rt.insert(new RouteItem("how/are/you",null));

        System.out.println(rt);

    }
    @Test
    public void testTree2(){
        RouteTree rt = new RouteTree(null, "GET");
        rt.insert(new RouteItem("user/getall",null));
        rt.insert(new RouteItem("user/getMy",null));
        rt.insert(new RouteItem("channel/add/one",null));


        System.out.println(rt);

    }

}
