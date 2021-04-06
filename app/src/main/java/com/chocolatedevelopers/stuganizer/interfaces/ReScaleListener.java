package com.chocolatedevelopers.stuganizer.interfaces;

import java.io.File;

public interface ReScaleListener {
    void onFinish(File file, boolean success, String message);
}
