package reader;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpIdReqest {
    public static String getId(String ... v) throws UnknownHostException,MalformedURLException
    {
        int ContentLength = 0;
        int cnt = 10;
        ArrayList<Character> gif = new ArrayList();
        StringBuffer bufa = new StringBuffer();

       try{
           String makeReqest = "https://translate.yandex.ru/?clid=2233626";
           URI dsfs = new URI(makeReqest);
           URL hp = new URL(dsfs.toASCIIString());

           HttpsURLConnection hpCon = (HttpsURLConnection) hp.openConnection();
           hpCon.setRequestProperty("Cookie","_ym_uid=15557589031013669715; mda=0; yandex_login=vipipclient; first_visit=1; first_visit_src=internal; stoken=818cf5b1.4bb54f60; fuid01=5d00b2ce53572dee.V8CnKr5DJZi8qAg5S-Ojh_qYiL3oay03FhQJfGmpkE00iGpT-zxrM2PUJoGalkr4iOwzWFb5GYjLWGLsGnGo9TtPhpaFZ0qPXfcCuNPOuhni34fJKYIOJ4hBv_LT4jTX; _ym_uid=15557589031013669715; _ym_d=1564230950; my=YysBgNUA; yuidss=6164412541555758745; ymex=1896322292.yrts.1580962292#1880119476.yrtsi.1564759476; yandex_gid=213; gdpr=0; L=XUhZZkBNZ1NVCHtbeAdaXwRbD1wHYAcCBg0bLCFaCVghOgI=.1582975182.14156.353911.50116cca114f5c8354fc900dadacf6d5; yandexuid=6164412541555758745; cycada=ZP0yf0OiYs59Bqibrmq/xyF4V5O8DZOvRZ0wjkd7XB4=; zm=m-white_bender.webp.css-https%3As3home-static_EeEoz3jxzx11ZKdZQwfAZtRP0k8%3Al; Session_id=3:1594999379.5.0.1582975182305:AeNIXw:6.1|28309186.0.2|220077.897455.qCE0kife-c9qVb9PsuSnOvVH2Nk; sessionid2=3:1594999379.5.0.1582975182305:AeNIXw:6.1|28309186.0.2|220077.649956.VUG93TCiTgJiMSo24mg35mvUqXc; i=WELwCSgxffBjURghQPUmv/0pXFinoE9BcELd9+OMEojV7bktkKIbwbeLaq+3I/wYXGfW6gNQpuqgHYv9T5dc8phmS1g=; _ym_isad=2; ys=def_bro.1#svt.1#wprid.1595179228870224-1689016039627246051300305-production-app-host-vla-web-yp-267#ybzcc.ru; yc=1595441944.zen.cach%3A1595186318; yabs-frequency=/5/4m0E04eB4r_rIzPU/6VboS000000mF27A-N9m0000030yGI8WG6q00000C3n1c4m-RG00000mF200/; yp=1617459192.brd.0201003190#1617459192.cld.2270452#1884325645.sad.1560597248:1568965645:3#1610951204.szm.1:1280x960:695x844#1898335182.udn.cDp2aXBpcGNsaWVudA%3D%3D#1613842950.ygu.0#1880119476.yrtsi.1564759476#1613842950.ygo.10739:213#1597861138.csc.2#1623518433.stltp.serp_bk-map_1_1591982433#1595343709.clh.2270456#1595247121.nwcst.1595161800_213_3#1595356009.gpauto.55_744712:37_961338:140:0:1595183199#1595773919.zmblt.1661#1595773919.zmbbr.yandexbrowser:20_7_1_68; _ym_visorc_50377519=b; _ym_d=1595183219; _ym_visorc_28584306=b");
           ContentLength = hpCon.getContentLength();
            Map<String, List<String>> gf = hpCon.getHeaderFields();
            gf.forEach((a,b) -> {
                Iterator<String> sdf = b.listIterator();
                while (sdf.hasNext())
                    System.out.println(String.format("%15s\t%s",a,sdf.next()));

            });


           InputStream asd = hpCon.getInputStream();
            byte[] buf = asd.readAllBytes();
            String Sozdik = new String(buf, StandardCharsets.UTF_8);
//            System.out.println(Sozdik);
            for (int i = 0; i < buf.length; i++) {
                bufa.append((char) buf[i]);
                gif.add((char) buf[i]);
            }
           String htmlString = Sozdik;

           System.out.println(Sozdik);
           System.out.println(htmlString.substring(htmlString.indexOf("SID: '")+"SID: '".length(),htmlString.indexOf("',",htmlString.indexOf("SID: '"))));
           String token = htmlString.substring(htmlString.indexOf("SID: '") + "SID: '".length(), htmlString.indexOf("',", htmlString.indexOf("SID: '")));
           StringTokenizer st = new StringTokenizer(token," \r/,\\«.'~@#$%^_+{}[]><=&-*?!—:;`»()\n");
           StringBuffer stringBuffer = new StringBuffer();
           while (st.hasMoreTokens())
               stringBuffer.append(new StringBuffer(st.nextToken()).reverse() + ".");
           stringBuffer.deleteCharAt(stringBuffer.length()-1);
//               System.out.println(new StringBuffer(st.nextToken()).reverse());
//           System.out.println(gif.toString().substring(gif.toString().indexOf("SID: '")));;
           System.out.println(stringBuffer.toString());
           return stringBuffer.toString();

        }catch (IOException | URISyntaxException e){System.out.println("EROOR");}finally {
           System.out.println("end of Id reqest");
       }

        return "";

    }
}
