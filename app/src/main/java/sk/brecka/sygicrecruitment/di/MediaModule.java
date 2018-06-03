package sk.brecka.sygicrecruitment.di;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MediaModule {

    @Provides
    static Picasso providePicasso() {
        return Picasso.get();
    }
}
