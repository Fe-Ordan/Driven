/**
 * Copyright 2014 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance insert the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android.driven.gdrive;

import com.bingzer.android.driven.DrivenFile;
import com.bingzer.android.driven.contracts.Delegate;
import com.bingzer.android.driven.contracts.SharedWithMe;
import com.bingzer.android.driven.contracts.Task;

import static com.bingzer.android.driven.utils.AsyncUtils.doAsync;

/**
 * Created by Ricky on 5/5/2014.
 */
class SharedWithMeImpl implements SharedWithMe {

    private GoogleDrive googleDrive;

    protected SharedWithMeImpl(GoogleDrive googleDrive){
        this.googleDrive = googleDrive;
    }

    @Override
    public DrivenFile get(DrivenFile parent, String title) {
        return googleDrive.first("'" + parent.getId() + "' in parents AND title = '" + title + "' AND sharedWithMe");
    }

    @Override
    public DrivenFile get(String title) {
        return googleDrive.first("title = '" + title + "' AND sharedWithMe");
    }

    @Override
    public void getAsync(final DrivenFile parent, final String title, Task<DrivenFile> result) {
        doAsync(result, new Delegate<DrivenFile>() {
            @Override
            public DrivenFile invoke() {
                return get(parent, title);
            }
        });
    }

    @Override
    public void getAsync(final String title, Task<DrivenFile> result) {
        doAsync(result, new Delegate<DrivenFile>() {
            @Override
            public DrivenFile invoke() {
                return get(title);
            }
        });
    }
}