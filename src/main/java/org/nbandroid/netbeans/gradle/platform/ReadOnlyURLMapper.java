/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.nbandroid.netbeans.gradle.platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.platform.JavaPlatform;
import org.netbeans.modules.masterfs.filebasedfs.FileBasedFileSystem;
import org.netbeans.modules.masterfs.filebasedfs.fileobjects.FileObjectFactory;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.URLMapper;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author arsi
 */
@ServiceProvider(service = URLMapper.class, position = 2000)
public class ReadOnlyURLMapper extends URLMapper implements PropertyChangeListener {

    private final AndroidPlatformProviderJava18 platformProvider = Lookup.getDefault().lookup(AndroidPlatformProviderJava18.class);
    private final AtomicReference<String[]> reference = new AtomicReference<>(new String[0]);

    public ReadOnlyURLMapper() {
        platformProvider.addPropertyChangeListener(this);
    }

    @Override
    public URL getURL(FileObject fo, int type) {
        return null;
    }

    @Override
    public FileObject[] getFileObjects(URL url) {
        String[] paths = reference.get();
        for (String path : paths) {
            if (url.getPath().startsWith(path)) {
                FileObject fileObject = FileBasedFileSystem.getFileObject(new File(url.getPath()), FileObjectFactory.Caller.ToFileObject);
                return new FileObject[]{new ReadOnlyFileObject(fileObject)};
            }
        }
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JavaPlatform[] installedPlatforms = platformProvider.getInstalledPlatforms();
        List<String> tmp = new ArrayList<>();
        for (JavaPlatform installedPlatform : installedPlatforms) {
            List<ClassPath.Entry> entries = installedPlatform.getSourceFolders().entries();
            for (ClassPath.Entry entrie : entries) {
                if (!tmp.contains(entrie.getURL().getPath())) {
                    tmp.add(entrie.getURL().getPath());
                }
            }
        }
        reference.set(tmp.toArray(new String[tmp.size()]));
    }

}
