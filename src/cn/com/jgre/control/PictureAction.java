package cn.com.jgre.control;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jgre.entity.Pictureinfo;
import cn.com.jgre.entity.ProductPicture;
import cn.com.jgre.entity.Userinfo;
import cn.com.jgre.service.PictureService;
import cn.com.jgre.service.ProductPictureService;
import cn.com.jgre.util.BaseParam;
import cn.com.jgre.util.DateUtil;
import cn.com.jgre.util.ImageUtil;

import com.alibaba.fastjson.JSON;


/**
 * 
 * <p>图片类</p>
 *
 * 类说明
 *
 * @author jgre
 * @version 1.0
 *
 * 创建时间 2014-11-7 下午08:16:30
 */
@Controller
public class PictureAction {

    
    @Resource
    PictureService pictureService;
    
    @Resource
    ProductPictureService productPictureService;
    
    
    /*
     * 上传图片
     */
    @RequestMapping(value="uploadFile.do",method=RequestMethod.POST)
    public String Upload(String categoryId,String extra1, float iHeight,float iWidth,@RequestParam MultipartFile[] myfiles, HttpServletRequest request){
        Userinfo loginUser=(Userinfo)request.getSession().getAttribute(BaseParam.LOGINUSER);
        @SuppressWarnings("deprecation")
        String UploadFilePath=request.getRealPath("//"+BaseParam.savePath);
        String message="success";
        for(MultipartFile myfile:myfiles){
            if(!myfile.isEmpty()){
                //取得当前上传文件名称
                String myFileName=  myfile.getOriginalFilename();
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if(!myFileName.trim().equals("")){
                    //重新命名
                    String ext=myFileName.substring(myFileName.lastIndexOf("."), myFileName.length());
                    String newfilename=DateUtil.formatDateyyMMdd()+new Random().nextInt(10000)+ext;
                    String newdir=createDirByDate(UploadFilePath);
                    String relativeUrl=BaseParam.savePath+"//"+newdir+"//"+newfilename;
                    String filePath=UploadFilePath+"//"+newdir+"//"+newfilename;
                    File localFile=new File(filePath);
                    localFile.setWritable(true,false);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    String filename=sdf.format(new Date());
                    String thumbPath=UploadFilePath.replaceFirst("uploadPicture", "thumbPicture")+"//"+filename;;
                    File file=new File(thumbPath);
                    file.setWritable(true, false);
                    if(file.exists()){
                        
                    }else{
                        file.mkdirs();
                    }
                    thumbPath+="//"+newfilename;
                    try {
                        myfile.transferTo(localFile);

                      //对图片进行压缩
                        ImageUtil.createThumbnail(filePath,thumbPath,iHeight,iWidth);
                        Pictureinfo tempPic=new Pictureinfo();
                        tempPic.setPicture_category_id(categoryId);
                        tempPic.setPicture_author(loginUser==null?"nouer":loginUser.getUsername());  
                        tempPic.setPucture_url(relativeUrl);
                        tempPic.setPicture_name(myFileName);
                        tempPic.setPicture_newname(newfilename);
                        tempPic.setPicture_id(String.valueOf(UUID.randomUUID()));
                        tempPic.setPicture_uploadtime(DateUtil.getCurrentTime());
                        tempPic.setPicture_showOnIndex("0");
                        tempPic.setExtra1(extra1);
                        pictureService.addPicture(tempPic);
                        System.out.println("thumbPath:" + thumbPath);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        message=e.getMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                        message=e.getMessage();
                    }
                }
            }
        }
        return "{msg:\""+message+"\",imgURL:\"\"}";
    }
    
    @RequestMapping(value="upddatePictureInfo.do",method=RequestMethod.POST)
    public String upddatePictureInfo(Pictureinfo pictureinfo,Model model){
        Pictureinfo p=pictureService.getPictureById(pictureinfo.getPicture_id());
        p.setPicture_showOnIndex(pictureinfo.getPicture_showOnIndex());
       int  ret= pictureService.updatePicture(p);
       if(ret>0){
           
       }else{
           model.addAttribute("errorMsg", "error");
       }
        return "common/jsp/json";
    }
    
    /*
     * 上传图片
     */
    @ResponseBody
    @RequestMapping(value="uploadProductImg.do",method=RequestMethod.POST)
    public String uploadProductImg(String categoryId,@RequestParam MultipartFile[] myfiles, HttpServletRequest request){
        Userinfo loginUser=(Userinfo)request.getSession().getAttribute(BaseParam.LOGINUSER);
        @SuppressWarnings("deprecation")
        String UploadFilePath=request.getRealPath("//"+BaseParam.savePath);
        String message="success";
        List<Pictureinfo> pictureinfos = new ArrayList<Pictureinfo>();
        for(MultipartFile myfile:myfiles){
        	if(!myfile.isEmpty()){
                //取得当前上传文件名称
                String myFileName=  myfile.getOriginalFilename();
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if(!myFileName.trim().equals("")){
                    //重新命名
                    String ext=myFileName.substring(myFileName.lastIndexOf("."), myFileName.length());
                    String newfilename=DateUtil.formatDateyyMMdd()+new Random().nextInt(10000)+ext;
                    String newdir=createDirByDate(UploadFilePath);
                    String relativeUrl=BaseParam.savePath+"//"+newdir+"//"+newfilename;
                    String filePath=UploadFilePath+"//"+newdir+"//"+newfilename;
                    File localFile=new File(filePath);
                    localFile.setWritable(true, false);
                    String thumbPath=UploadFilePath.replaceFirst("uploadPicture", "thumbPicture");
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    String filename=sdf.format(new Date());
                    File file=new File(thumbPath+"//"+filename);
                    file.setWritable(true, false);
                    if(file.exists()){
                    }else{
                        file.mkdirs();
                    }
                    
                    thumbPath = thumbPath + "//" + filename + "//" +newfilename;
                    try {
                        myfile.transferTo(localFile);

                      //对图片进行压缩
                        ImageUtil.createThumbnail(filePath,thumbPath,242f,155f);
                        Pictureinfo tempPic=new Pictureinfo();
                        tempPic.setPicture_category_id(categoryId);
                        tempPic.setPicture_author(loginUser==null?"nouer":loginUser.getUsername());  
                        tempPic.setPucture_url(relativeUrl);
                        tempPic.setPicture_name(myFileName);
                        tempPic.setPicture_newname(newfilename);
                        tempPic.setPicture_id(String.valueOf(UUID.randomUUID()));
                        tempPic.setPicture_uploadtime(DateUtil.getCurrentTime());
                        tempPic.setPicture_showOnIndex("0");
                        pictureService.addPicture(tempPic);
                        pictureinfos.add(tempPic);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        message=e.getMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                        message=e.getMessage();
                    }
                }
            }
        }
        return JSON.toJSONString(pictureinfos);
    }
    
    /*
     * 获取图片
     */
    @RequestMapping(value="getPictures.do",method=RequestMethod.POST)
    public String getPictures(Pictureinfo picture, int offset, int limit, Model model){
       if("all".equalsIgnoreCase(picture.getPicture_category_id())){
           picture.setPicture_category_id(null);
       }
        List<Pictureinfo> pictures=pictureService.getPictures(picture, offset, limit);
        int totalProperty= pictureService.getTotal(picture);
        model.addAttribute("totalProperty", totalProperty);
        model.addAttribute("jsonString", JSON.toJSONString(pictures));
//        response.setHeader("Access-Control-Allow-Origin", "*");
        return "common/jsp/json";
    }
  
    /*
     * 删除
     */
    @RequestMapping(value="deletePicture.do",method=RequestMethod.POST)
    public String deleteArticle(@RequestParam String deleteId,Model model,HttpServletRequest request){
        if(deleteId!=null){
            String errorMsg="";
            String[] ids=deleteId.split("\\$");
            for(String id:ids){
                Pictureinfo p=pictureService.getPictureById(id);
                //先删除数据库，再删除物理文件
               int ret=pictureService.deletePicture(p);
               if(ret<0){
                   errorMsg=errorMsg+id;
                   model.addAttribute("errorMsg", "error delete database file");
               }else{
                 //删除物理文件
                   if(deleteFile(p.getPucture_url(),request)){
                       
                   }else{
                       model.addAttribute("errorMsg", "error delete disk file");  
                   }
               }
            }
        }
        return "common/jsp/json";
    }
    
	/*
	 * 删除
	 */
	@RequestMapping(value = "deleteProductPicture.do", method = RequestMethod.POST)
	public String deleteProductPicture(@RequestParam String deleteId, Model model, HttpServletRequest request) {
		if (deleteId != null) {
			String errorMsg = "";
			String[] ids = deleteId.split("\\$");
			for (String id : ids) {
				String[] ppIds = id.split("&");
				if (ppIds.length == 2) {
					String picId = ppIds[0];
					String proId = ppIds[1];
					Pictureinfo p = pictureService.getPictureById(picId);
					// 先删除数据库，再删除物理文件
					if (p != null) {
						int ret = pictureService.deletePicture(p);
						if (ret < 0) {
							errorMsg = errorMsg + id;
							model.addAttribute("errorMsg", "error delete database file");
						} else {
							// 删除物理文件
							if (deleteFile(p.getPucture_url(), request)) {

							} else {
								model.addAttribute("errorMsg", "error delete disk file");
							}
						}
					}
					ProductPicture pp = new ProductPicture();
					pp.setProduct_id(proId);
					pp.setPicture_id(picId);
					productPictureService.delRecordByPicIdAndProId(pp);
				}
			}
		}
		return "common/jsp/json";
	}
    
    /*
     * 按照日期创建文件夹
     */
    public String createDirByDate(String path){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String filename=sdf.format(new Date());
        File file=new File(path+"//"+filename);
        file.setWritable(true, false);
        if(file.exists()){
            
        }else{
            file.mkdirs();
        }
       return filename;
   }
    
    /*
     * 删除物理文件
     */
    public static boolean deleteFile(String fileName,HttpServletRequest request) {
        String path =request.getSession().getServletContext().getRealPath("//");
        System.out.println("delete path"+path);
        String filePath = path + "\\" + fileName;
        System.out.println(filePath);
        File file = new File(filePath);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                System.out.println("删除文件错误");
                return false;
            }
        } else {
            System.out.println("文件不存在");
            return false;
        }
    }
    
   
}
