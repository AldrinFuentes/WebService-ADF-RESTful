package restservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import oracle.adf.share.ADFContext;
import oracle.jbo.client.Configuration;

import restmodel.common.RESTAppModule;


@Path("restservice")
public class RESTService {
    public RESTService() {
        super();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/x-www-form-urlencoded")
    public String Test_Comunicacion(String message){
        
        String        qualifiedAMDefName = "restmodel.RESTAppModule";
        String        configName = "RESTAppModuleLocal";
        String        resultado = null;
        RESTAppModule am = null;
        ADFContext ctx = null; 
        
        try{
            ctx = ADFContext.initADFContext(null, null, null, null);
            am = (RESTAppModule) Configuration.createRootApplicationModule(qualifiedAMDefName, configName);
            resultado = am.testComunicacion(message);

        }catch(Exception ex){
           ex.printStackTrace();
        }finally{
           Configuration.releaseRootApplicationModule(am, false);
            ADFContext.resetADFContext(ctx);
        }     
        return resultado;
    }
}
