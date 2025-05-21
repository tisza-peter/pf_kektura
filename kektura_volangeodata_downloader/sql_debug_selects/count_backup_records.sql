select count(*), 'agency' as tablename from gtfs_backup.agency
union
select count(*), 'calendar' as tablename from gtfs_backup.calendar
union
select count(*), 'calendar_dates' as tablename from gtfs_backup.calendar_dates
union
select count(*), 'feed_info' as tablename from gtfs_backup.feed_info
union
select count(*), 'routes' as tablename from gtfs_backup.routes
union
select count(*), 'shapes' as tablename from gtfs_backup.shapes
union
select count(*), 'stop_times' as tablename from gtfs_backup.stop_times
union
select count(*), 'stops' as tablename from gtfs_backup.stops
union
select count(*), 'trips' as tablename from gtfs_backup.trips
union
select count(*), 'shape_route' as tablename from geom_backup.shape_route
union
select count(*), 'shape_stop' as tablename from geom_backup.shape_stop
