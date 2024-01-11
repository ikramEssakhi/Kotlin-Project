class Playlist {
  int id;
  String title;
  String description;
  int duration;
  bool isPublic;
  bool isLovedTrack;
  bool isCollaborative;
  int trackCount;
  int unseenTrackCount;
  int fans;
  String link;
  String share;
  String picture;
  String pictureSmall;
  String pictureMedium;
  String pictureBig;
  String pictureXl;
  String checksum;
  User creator;
  List<Track> tracks;

  Playlist({
    required this.id,
    required this.title,
    required this.description,
    required this.duration,
    required this.isPublic,
    required this.isLovedTrack,
    required this.isCollaborative,
    required this.trackCount,
    required this.unseenTrackCount,
    required this.fans,
    required this.link,
    required this.share,
    required this.picture,
    required this.pictureSmall,
    required this.pictureMedium,
    required this.pictureBig,
    required this.pictureXl,
    this.checksum = '',
    required this.creator,
    required this.tracks,
  });

  factory Playlist.fromJson(Map<String, dynamic> json) {
    return Playlist(
      id: json['id'],
      title: json['title'],
      description: json['description'],
      duration: json['duration'],
      isPublic: json['public'],
      isLovedTrack: json['is_loved_track'],
      isCollaborative: json['collaborative'],
      trackCount: json['nb_tracks'],
      unseenTrackCount: json['unseen_track_count'],
      fans: json['fans'],
      link: json['link'],
      share: json['share'],
      picture: json['picture'],
      pictureSmall: json['picture_small'],
      pictureMedium: json['picture_medium'],
      pictureBig: json['picture_big'],
      pictureXl: json['picture_xl'],
      checksum: json['checksum'] ?? '',
      creator: User.fromJson(json['creator']),
      tracks: (json['tracks'] as List<dynamic>)
          .map((trackJson) => Track.fromJson(trackJson))
          .toList(),
    );
  }
}

class User {
  int id;
  String name;
  String link;

  User({
    required this.id,
    required this.name,
    required this.link,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'],
      name: json['name'],
      link: json['link'],
    );
  }
}

class Track {
  int id;
  bool readable;
  String title;
  String titleShort;
  String titleVersion;
  bool unseen;
  String link;
  int duration;
  int rank;
  bool explicitLyrics;
  String preview;
  DateTime timeAdd;
  Artist artist;
  Album album;

  Track({
    required this.id,
    required this.readable,
    required this.title,
    required this.titleShort,
    required this.titleVersion,
    required this.unseen,
    required this.link,
    required this.duration,
    required this.rank,
    required this.explicitLyrics,
    required this.preview,
    required this.timeAdd,
    required this.artist,
    required this.album,
  });

  factory Track.fromJson(Map<String, dynamic> json) {
    return Track(
      id: json['id'],
      readable: json['readable'],
      title: json['title'],
      titleShort: json['title_short'],
      titleVersion: json['title_version'],
      unseen: json['unseen'],
      link: json['link'],
      duration: json['duration'],
      rank: json['rank'],
      explicitLyrics: json['explicit_lyrics'],
      preview: json['preview'],
      timeAdd: DateTime.parse(json['time_add'] ?? ''),
      artist: Artist.fromJson(json['artist']),
      album: Album.fromJson(json['album']),
    );
  }
}

class Artist {
  int id;
  String name;
  String link;

  Artist({
    required this.id,
    required this.name,
    required this.link,
  });

  factory Artist.fromJson(Map<String, dynamic> json) {
    return Artist(
      id: json['id'],
      name: json['name'],
      link: json['link'],
    );
  }
}

class Album {
  int id;
  String title;
  String cover;
  String coverSmall;
  String coverMedium;
  String coverBig;
  String coverXl;

  Album({
    required this.id,
    required this.title,
    required this.cover,
    required this.coverSmall,
    required this.coverMedium,
    required this.coverBig,
    required this.coverXl,
  });

  factory Album.fromJson(Map<String, dynamic> json) {
    return Album(
      id: json['id'],
      title: json['title'],
      cover: json['cover'],
      coverSmall: json['cover_small'],
      coverMedium: json['cover_medium'],
      coverBig: json['cover_big'],
      coverXl: json['cover_xl'],
    );
  }
}
