/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nbandroid.netbeans.gradle.v2.sdk.ui;

import com.sun.javafx.PlatformUtil;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.gradle.impldep.org.apache.commons.io.FileUtils;
import org.nbandroid.netbeans.gradle.v2.maven.MavenDownloader;
import org.nbandroid.netbeans.gradle.v2.sdk.AndroidSdkImpl;
import static org.nbandroid.netbeans.gradle.v2.sdk.ui.SDKVisualPanelInstall.SDK_INSTALLED;
import org.openide.modules.Places;
import org.openide.util.Exceptions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author arsi
 */
public class PanelDownloadToBrokenSDK extends javax.swing.JPanel {

    public static final String GOOGLE_INDEX = "https://dl.google.com/android/repository/repository.xml";
    public static final String SDKLICENSE = "sdk:license";
    public static final String SDKURL = "sdk:url";
    public static final String MACOSX = "macosx";
    public static final String WINDOWS = "windows";
    public static final String LINUX = "linux";
    public static final String TOOLS = "tools";
    public static final String GOOGLE_REPO_URL = "https://dl.google.com/android/repository/";
    public static final String NBANDROID_FOLDER = "nbandroid/";
    public static final String PLATFORMTOOLS = "platform-tools";
    public static final String DOWNLOAD_OK = "DOWNLOAD_OK";
    private final File repository = Places.getCacheSubfile("nbandroid/repository.xml");
    private String license = "Unable to download https://dl.google.com/android/repository/repository.xml";
    private final List<String> files = new ArrayList<>();
    private File platformTools;
    private File tools;
    private final AndroidSdkImpl sdk;

    /**
     * Creates new form PanelDownloadToBrokenSDK
     */
    public PanelDownloadToBrokenSDK(AndroidSdkImpl sdk) {
        this.sdk = sdk;
        initComponents();
        warning.setVisible(false);
        try {
            MavenDownloader.downloadFully(new URL(GOOGLE_INDEX), repository);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        if (repository.exists()) {
            try {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document parse = docBuilder.parse(repository);
                NodeList elementsByTagName = parse.getElementsByTagName(SDKLICENSE);
                int length = elementsByTagName.getLength();
                if (length == 1) {
                    Node item = elementsByTagName.item(0);
                    license = item.getTextContent();
                }
                elementsByTagName = parse.getElementsByTagName(SDKURL);
                length = elementsByTagName.getLength();
                String filter = "**********";
                if (PlatformUtil.isLinux()) {
                    filter = LINUX;
                } else if (PlatformUtil.isWindows()) {
                    filter = WINDOWS;
                } else if (PlatformUtil.isMac()) {
                    filter = MACOSX;
                }
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        Node item = elementsByTagName.item(i);
                        String fileName = item.getTextContent();
                        if (fileName.contains(filter)) {
                            files.add(fileName);
                        }

                    }
                }

            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Exceptions.printStackTrace(ex);
            }
            jTextPane1.setText(license);
            jTextPane1.setCaretPosition(0);
            if (files.isEmpty()) {
                downloadButton.setEnabled(false);
            } else {
                downloadButton.setEnabled(true);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jProgressBar1 = new javax.swing.JProgressBar();
        downloadButton = new javax.swing.JButton();
        downloadText = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        progress = new javax.swing.JProgressBar();
        install = new javax.swing.JButton();
        warning = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(676, 390));
        setMinimumSize(new java.awt.Dimension(676, 390));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PanelDownloadToBrokenSDK.class, "PanelDownloadToBrokenSDK.jPanel1.border.title"))); // NOI18N

        jScrollPane1.setViewportView(jTextPane1);

        org.openide.awt.Mnemonics.setLocalizedText(downloadButton, org.openide.util.NbBundle.getMessage(PanelDownloadToBrokenSDK.class, "PanelDownloadToBrokenSDK.downloadButton.text")); // NOI18N
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(downloadText, org.openide.util.NbBundle.getMessage(PanelDownloadToBrokenSDK.class, "PanelDownloadToBrokenSDK.downloadText.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(downloadText, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(downloadButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(downloadButton)
                    .addComponent(downloadText))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {downloadButton, downloadText});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PanelDownloadToBrokenSDK.class, "PanelDownloadToBrokenSDK.jPanel2.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(install, org.openide.util.NbBundle.getMessage(PanelDownloadToBrokenSDK.class, "PanelDownloadToBrokenSDK.install.text")); // NOI18N
        install.setEnabled(false);
        install.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                installActionPerformed(evt);
            }
        });

        warning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/nbandroid/netbeans/gradle/v2/sdk/ui/warning-badge.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(warning, org.openide.util.NbBundle.getMessage(PanelDownloadToBrokenSDK.class, "PanelDownloadToBrokenSDK.warning.text")); // NOI18N
        warning.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(warning)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(install)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(install)
                    .addComponent(warning))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        downloadButton.setEnabled(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < files.size(); i++) {
                    String fileName = files.get(i);
                    if (fileName.startsWith(PLATFORMTOOLS)) {
                        platformTools = Places.getCacheSubfile(NBANDROID_FOLDER + fileName);
                        downloadText.setText("Downloading " + fileName + "...");
                        if (!platformTools.exists() || platformTools.length() == 0) {
                            try {

                                MavenDownloader.downloadFully(new URL(GOOGLE_REPO_URL + fileName), platformTools, jProgressBar1);
                            } catch (MalformedURLException ex) {
                                Exceptions.printStackTrace(ex);
                            } catch (IOException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        } else {
                            jProgressBar1.setValue(jProgressBar1.getMaximum());
                        }
                        downloadText.setText("Downloading " + fileName + "... Done!");
                    } else if (fileName.startsWith(TOOLS)) {
                        tools = Places.getCacheSubfile(NBANDROID_FOLDER + fileName);
                        downloadText.setText("Downloading " + fileName + "...");
                        if (!tools.exists() || tools.length() == 0) {
                            try {
                                MavenDownloader.downloadFully(new URL(GOOGLE_REPO_URL + fileName), tools, jProgressBar1);
                            } catch (MalformedURLException ex) {
                                Exceptions.printStackTrace(ex);
                            } catch (IOException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        } else {
                            jProgressBar1.setValue(jProgressBar1.getMaximum());
                        }
                        downloadText.setText("Downloading " + fileName + "... Done!");
                    }
                }
                firePropertyChange(DOWNLOAD_OK, true, false);
                install.setEnabled(true);
            }

        };
        MavenDownloader.POOL.execute(runnable);
    }//GEN-LAST:event_downloadButtonActionPerformed

    private void installActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_installActionPerformed
        install.setEnabled(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progress.setIndeterminate(true);
                String destDir = sdk.getSdkPath();
                File f = new File(destDir);

                if (f.list().length > 0) {
                    try {
                        FileUtils.deleteDirectory(new File(destDir));
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    f.mkdirs();
                }
                try {
                    MavenDownloader.unzip(tools, new File(destDir));
                    MavenDownloader.unzip(platformTools, new File(destDir));
                } catch (IOException ex) {
                    warning.setText("An error occurred while extracting zip file!");
                    warning.setVisible(true);
                } finally {
                    progress.setIndeterminate(false);
                    progress.setValue(progress.getMaximum());
                    firePropertyChange(SDK_INSTALLED, false, true);
                }
            }
        };
        MavenDownloader.POOL.execute(runnable);
    }//GEN-LAST:event_installActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downloadButton;
    private javax.swing.JLabel downloadText;
    private javax.swing.JButton install;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JProgressBar progress;
    private javax.swing.JLabel warning;
    // End of variables declaration//GEN-END:variables
}
