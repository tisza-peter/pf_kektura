select count(*), 'agency' as tablename from gtfs_staging.agency
union
select count(*), 'calendar' as tablename from gtfs_staging.calendar
union
select count(*), 'calendar_dates' as tablename from gtfs_staging.calendar_dates
union
select count(*), 'feed_info' as tablename from gtfs_staging.feed_info
union
select count(*), 'routes' as tablename from gtfs_staging.routes
union
select count(*), 'shapes' as tablename from gtfs_staging.shapes
union
select count(*), 'stop_times' as tablename from gtfs_staging.stop_times
union
select count(*), 'stops' as tablename from gtfs_staging.stops
union
select count(*), 'trips' as tablename from gtfs_staging.trips
union
select count(*), 'shape_route' as tablename from geom_staging.shape_route
union
select count(*), 'shape_stop' as tablename from geom_staging.shape_stop
