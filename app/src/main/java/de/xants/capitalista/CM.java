/*
 * Copyright 2015 https://github.com/donmahallem/VivaCapitalista
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.xants.capitalista;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

public class CM {

    private final static long CACHE_SIZE = 50 * 1024 * 1024;
    private static Bus BUS = new Bus();
    private static OkHttpClient mOkHttpClient;
    private static Cache mOkHttpClientCache;
    private static Picasso mPicasso;

    private static Context mContext;

    static {
        mOkHttpClient = new OkHttpClient();
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static Picasso getPicasso() {
        if (mPicasso == null) {
            mPicasso = new Picasso.Builder(mContext)
                    .indicatorsEnabled(true)
                    .loggingEnabled(true)
                    .downloader(new OkHttpDownloader(getOkHttpClient()))
                    .build();
        }
        return mPicasso;
    }

    public static void init(Context context) {
        mContext = context;
        if (mContext != null) {
            mOkHttpClientCache = new Cache(new File(context.getCacheDir(), "okhttp"), CACHE_SIZE);
            mOkHttpClient.setCache(mOkHttpClientCache);
        }
    }

    public static Bus getBus() {
        return BUS;
    }


}
