package com.otto.ProjectSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * Utility class containing static methods
 * for Spring framework
 *
 */
public class ImageUtils {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Method for saving image in MultipartFile representation to the specified directory on hard disc
     *
     *
     * @param image - MultipartFile(image that needs to be saved)
     * @param request - HttpServletRequest needed to get root directory
     * @param folderStructure - explicit folder structure of your project, for example: "\\WEB-INF\\resources\\images"
     * @param id - name of the resulting image file
     *
     *
     */

    public static void saveImage(MultipartFile image, HttpServletRequest request, String folderStructure, int id){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        Path path = Paths.get(rootDirectory + folderStructure + id +".png");
        if(image!=null && !image.isEmpty()){
            try{
                image.transferTo(new File(path.toString()));
                LOG.info("Saved image for " + id);
            } catch (IOException e) {
                LOG.error("Failed to save image for " + id);
            }
        }
    }

    /**
     * Method for deleting picture from HD
     *
     * @param request - HttpServletRequest needed to get root directory
     * @param folderStructure - explicit folder structure of your project, for example: "\\WEB-INF\\resources\\images"
     * @param id- name of the image file
     */

    public static void deleteImage(HttpServletRequest request, String folderStructure, int id){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        Path path = Paths.get(rootDirectory + folderStructure + id +".png");

        if(Files.exists(path)){
            try{
                Files.delete(path);
                LOG.info("Deleted image for " + id);
            } catch (IOException e) {
                LOG.error("Failed to delete image for " + id);
            }
        }
    }
}
